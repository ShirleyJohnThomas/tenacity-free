package com.tenacity.free.common.image;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.image
 * @file_name: ImageUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午7:53:37
 * @desc: 图片压缩工具类
 */
public class ImageUtils {

    private ImageUtils() {
    }

    /**
     * @param img
     * @param width
     * @param height
     * @description 转换图片大小，不变形
     * @author Blossom_zhang
     * @date 2017年8月9日 下午9:54:00
     */
    public static final void changeImge(File img, int width, int height) {
        try {
            Thumbnails.of(img).size(width, height).keepAspectRatio(false).toFile(img);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("图片转换出错！", e);
        }
    }

    /**
     * @param orgImg
     * @param scale
     * @param targetFile
     * @throws IOException
     * @description 根据比例缩放图片
     * @author Blossom_zhang
     * @date 2017年8月9日 下午9:54:14
     */
    public static final void scale(BufferedImage orgImg, double scale, String targetFile) throws IOException {
        Thumbnails.of(orgImg).scale(scale).toFile(targetFile);
    }

    public static final void scale(String orgImgFile, double scale, String targetFile) throws IOException {
        Thumbnails.of(orgImgFile).scale(scale).toFile(targetFile);
    }

    /**
     * @param orgImgFile
     * @param width
     * @param height
     * @param suffixName
     * @param targetFile
     * @throws IOException
     * @description 图片格式转换
     * @author Blossom_zhang
     * @date 2017年8月9日 下午9:54:26
     */
    public static final void format(String orgImgFile, int width, int height, String suffixName, String targetFile)
            throws IOException {
        Thumbnails.of(orgImgFile).size(width, height).outputFormat(suffixName).toFile(targetFile);
    }

    /**
     * @param orgImg
     * @param targetWidth
     * @param targetFile
     * @return
     * @throws IOException
     * @description 根据宽度同比缩放
     * @author Blossom_zhang
     * @date 2017年8月9日 下午9:54:40
     */
    public static final double scaleWidth(BufferedImage orgImg, int targetWidth, String targetFile) throws IOException {
        int orgWidth = orgImg.getWidth();
        // 计算宽度的缩放比例
        double scale = targetWidth * 1.00 / orgWidth;
        // 裁剪
        scale(orgImg, scale, targetFile);

        return scale;
    }

    public static final void scaleWidth(String orgImgFile, int targetWidth, String targetFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(orgImgFile));
        scaleWidth(bufferedImage, targetWidth, targetFile);
    }

    /**
     * @param orgImg
     * @param targetHeight
     * @param targetFile
     * @return
     * @throws IOException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:55:58
     * @desc: 根据高度同比缩放
     */
    public static final double scaleHeight(BufferedImage orgImg, int targetHeight, String targetFile)
            throws IOException {
        int orgHeight = orgImg.getHeight();
        double scale = targetHeight * 1.00 / orgHeight;
        scale(orgImg, scale, targetFile);
        return scale;
    }

    public static final void scaleHeight(String orgImgFile, int targetHeight, String targetFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(orgImgFile));
        // int height = bufferedImage.getHeight();
        scaleHeight(bufferedImage, targetHeight, targetFile);
    }

    /**
     * @param file
     * @param width
     * @throws IOException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:56:07
     * @desc: 原始比例缩放
     */
    public static final void scaleWidth(File file, Integer width) throws IOException {
        String fileName = file.getName();
        String filePath = file.getAbsolutePath();
        String postFix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        // 缩放
        BufferedImage bufferedImg = ImageIO.read(file);
        String targetFile = filePath + "_s" + postFix;
        scaleWidth(bufferedImg, width, targetFile);
        String targetFile2 = filePath + "@" + width;
        new File(targetFile).renameTo(new File(targetFile2));
    }

    /**
     * @param src    源文件
     * @param dest   目标文件
     * @param startX 起点x坐标
     * @param startY 起点y坐标
     * @param endX   结束点x坐标
     * @param endY   结束点y坐标
     * @throws IOException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:56:17
     * @desc: 切图
     */
    public static void cut(String src, String dest, int startX, int startY, int endX, int endY) throws IOException {
        //获取文件扩展名
        String suffix = src.substring(src.lastIndexOf(".") + 1);
        //读入文件
        BufferedImage bi = ImageIO.read(new File(src));
        //计算宽度
        int width = Math.abs(startX - endX);
        //计算高度
        int height = Math.abs(startY - endY);
        BufferedImage target = bi.getSubimage(startX, startY, width, height);
        ImageIO.write(target, suffix, new File(dest));
    }

    /**
     * @param src     源文件
     * @param dest    目标文件
     * @param degree  旋转角度
     * @param bgcolor 背景色，无背景色为null
     * @throws IOException
     * @author: free.zhang
     * @datetime: 2018年1月13日 下午7:56:34
     * @desc: 旋转图片
     */
    public static void rotate(String src, String dest, int degree, Color bgcolor) throws IOException {
        BufferedImage image = ImageIO.read(new File(src));
        int iw = image.getWidth();// 原始图象的宽度
        int ih = image.getHeight();// 原始图象的高度
        int w = 0;
        int h = 0;
        int x = 0;
        int y = 0;
        degree = degree % 360;
        if (degree < 0)
            degree = 360 + degree;// 将角度转换到0-360度之间
        double ang = Math.toRadians(degree);// 将角度转为弧度

        /**
         * 确定旋转后的图象的高度和宽度
         */

        if (degree == 180 || degree == 0 || degree == 360) {
            w = iw;
            h = ih;
        } else if (degree == 90 || degree == 270) {
            w = ih;
            h = iw;
        } else {
            double cosVal = Math.abs(Math.cos(ang));
            double sinVal = Math.abs(Math.sin(ang));
            w = (int) (sinVal * ih) + (int) (cosVal * iw);
            h = (int) (sinVal * iw) + (int) (cosVal * ih);
        }

        x = (w / 2) - (iw / 2);// 确定原点坐标
        y = (h / 2) - (ih / 2);
        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        Graphics2D gs = (Graphics2D) rotatedImage.getGraphics();
        if (bgcolor == null) {
            rotatedImage = gs.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
        } else {
            gs.setColor(bgcolor);
            gs.fillRect(0, 0, w, h);// 以给定颜色绘制旋转后图片的背景
        }

        AffineTransform at = new AffineTransform();
        at.rotate(ang, w / 2, h / 2);// 旋转图象
        at.translate(x, y);
        AffineTransformOp op = new AffineTransformOp(at,
                AffineTransformOp.TYPE_BICUBIC);
        op.filter(image, rotatedImage);
        image = rotatedImage;

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ImageOutputStream iamgeOut = ImageIO.createImageOutputStream(byteOut);

        ImageIO.write(image, "png", iamgeOut);
        InputStream is = new ByteArrayInputStream(byteOut.toByteArray());

        OutputStream os = new FileOutputStream(new File(dest));

        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        os.close();
        is.close();
        byteOut.close();
    }

}
