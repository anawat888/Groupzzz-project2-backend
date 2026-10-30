package se331.zzz.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.UUID;

@Component
public class CloudStorageHelper {
    static final Map<String, String> IMAGE_EXTENSIONS = Map.of(
            "image/png", "png",
            "image/jpeg", "jpg",
            "image/gif", "gif",
            "image/webp", "webp");

    @Value("${supabase.storage.endpoint}")
    String endpoint;
    @Value("${supabase.storage.region}")
    String region;
    @Value("${supabase.storage.access-key}")
    String accessKey;
    @Value("${supabase.storage.secret-key}")
    String secretKey;
    @Value("${supabase.storage.bucket}")
    String bucket;
    @Value("${supabase.storage.public-url}")
    String publicUrl;

    S3Client s3Client;

    public static boolean isSupportedImage(String contentType) {
        return contentType != null && IMAGE_EXTENSIONS.containsKey(contentType);
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String key = UUID.randomUUID() + "." + IMAGE_EXTENSIONS.get(file.getContentType());
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(file.getContentType())
                .build();
        getClient().putObject(request, RequestBody.fromBytes(file.getBytes()));
        return publicUrl + "/" + bucket + "/" + key;
    }

    private synchronized S3Client getClient() {
        if (s3Client == null) {
            s3Client = S3Client.builder()
                    .endpointOverride(URI.create(endpoint))
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                    .forcePathStyle(true)
                    .build();
        }
        return s3Client;
    }
}
