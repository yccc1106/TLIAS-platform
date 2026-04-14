package org.ycc;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.File;

public class Demo {

    public static void main(String[] args) throws Exception {
        // Endpoint以华北2（北京）为例
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";

        // 从环境变量中获取访问凭证。运行前需确保已设置 OSS_ACCESS_KEY_ID 和 OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 填写Bucket名称
        String bucketName = "web-ai-ycc";

        // 填写保存在OSS上的Object完整路径（文件名）
        String objectName = "001.jpg";

        // 【修改点1】将实际要上传的本地文件路径统一写在这里
        String filePath = "/Users/yuanchengle/Pictures/头像.jpeg";

        // 填写Bucket所在地域，需与endpoint对应
        String region = "cn-beijing";

        // 创建OSSClient实例配置
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);

        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            // 【修改点2】直接使用明确的本地路径创建 File 对象
            File fileToUpload = new File(filePath);

            // 【优化项】在上传前检查本地文件是否存在，防止抛出 NoSuchFileException
            if (!fileToUpload.exists()) {
                System.err.println("错误：找不到本地文件，请检查路径 -> " + filePath);
                return;
            }

            // 创建上传请求
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, fileToUpload);

            // 上传文件
            PutObjectResult result = ossClient.putObject(putObjectRequest);

            // 【优化项】增加成功提示
            System.out.println("上传成功！");
            System.out.println("ETag: " + result.getETag());
            System.out.println("OSS Request ID: " + result.getRequestId());

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
                System.out.println("OSSClient 已安全关闭。");
            }
        }
    }
}