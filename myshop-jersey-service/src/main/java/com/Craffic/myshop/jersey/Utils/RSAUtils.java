package com.Craffic.myshop.jersey.Utils;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.commons.codec.binary.Base64;
import org.apache.kafka.common.protocol.types.Field;
import sun.java2d.pipe.OutlineTextRenderer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.rmi.ServerException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    /**
     * 加密算法
     */
    private static final String KEY_ALGORITYHM_RSA = "RSA";
    /**
     * 填充方式
      */
    private static final String CIPHER_PADDING = "RSA/ECB/PKCS1Padding";

    /**
     * 指定位数
     */
    private static final int KEY_SIZE = 512;

    /**
     * RSA最大解密密文字节数
     */
    private static final int MAX_DECRYPT_LENGTH = KEY_SIZE / 8;

    /**
     * RSA最大加密密文字节数
     */
    private static final int MAX_ENCRYPT_LENGTH = MAX_DECRYPT_LENGTH - 11;

    /**
     * @param data： 待加密数据
     * @param publicKeyStr： 公玥字符串
     */
    public static String encrypt(String data, String publicKeyStr) throws ServerException {
        ByteArrayOutputStream out = null;
        try {
            PublicKey publicKey = getPublicKey(publicKeyStr);
            Cipher cipher = Cipher.getInstance(CIPHER_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 编码
            URLEncoder.encode(data, "UTF-8");
            // 加号替换成%20
            data = data.replace("\\+", "%20");
            int inputLen = data.getBytes().length;
            out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache = new byte[]{};
            int i = 0;

            // 如果哟啊加密的数据过长，对数据进行分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_LENGTH){
                    cache = cipher.doFinal(data.getBytes("UTF-8"), offSet, MAX_ENCRYPT_LENGTH);
                } else {
                    cache = cipher.doFinal(data.getBytes("UTF-8"), offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_LENGTH;
            }
            // 加密后的字节数据
            byte[] encryptedData = out.toByteArray();
            // base64编码的返回结果
            return Base64.encodeBase64String(encryptedData);

        } catch (ServerException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.printf("RSA加密过程中出错：{}", e);
            e.printStackTrace();
            throw new ServerException("RSA加密过程中出错：{}");
        } finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.printf("关闭流失败");
                    e.printStackTrace();
                }
            }
        }


    }

    private static PublicKey getPublicKey(String publicKey) throws ServerException {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(KEY_ALGORITYHM_RSA);
            byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new ServerException("RSA获取公玥发生错误：{}", e);
        }
    }

    public static String decrypt(String data, String privateKeyStr){
        ByteArrayOutputStream out = null;
        try {
            // 获取私玥, 密码器
            PrivateKey privateKey = getPrivateKey(privateKeyStr);
            Cipher cipher = Cipher.getInstance(CIPHER_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] dataBytes = Base64.decodeBase64(data);
            int inputLen = dataBytes.length;
            out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache = new byte[]{};
            int i = 0;

            //
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_LENGTH){
                    cache = cipher.doFinal(dataBytes, offSet, MAX_DECRYPT_LENGTH);
                } else {
                    cache = cipher.doFinal(dataBytes, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_LENGTH;
            }
            byte[] decryptedData = out.toByteArray();
            String result = new String(decryptedData, "UTF-8");
            return URLDecoder.decode(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.printf("关闭流失败");
                    e.printStackTrace();
                }
            }
        }


        return null;
    }


    private static PrivateKey getPrivateKey(String privateKey) throws ServerException {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(KEY_ALGORITYHM_RSA);
            byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new ServerException("RSA获取私玥出错");
        }
    }

}
