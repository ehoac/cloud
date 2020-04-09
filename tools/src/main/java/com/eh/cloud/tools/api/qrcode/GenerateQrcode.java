package com.eh.cloud.tools.api.qrcode;

import com.eh.cloud.tools.util.QrCodeUtil;
import com.eh.cloud.tools.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caopeihe
 * @date 2020/3/9 11:15
 * @desc GenerateQrcode
 */
@RestController
@RequestMapping("qrcode")
public class GenerateQrcode {
    @GetMapping("gen")
    public Result qrCode() throws Exception{
        // 存放在二维码中的内容
        String text = buildUrl();
        // 嵌入二维码的图片路径
        String imgPath = "G:/qrCode/dog.jpg";
        // 生成的二维码的路径及名称
        String destPath = "G:/qrCode/qrcode/handSign.jpg";
        //生成二维码
        QrCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
//        String str = QrCodeUtil.decode(destPath);
        // 打印出解析出的内容
//        System.out.println(str);
        Result result = Result.info(200, "img");
        return result;
    }

    private String buildUrl(){
        StringBuilder sb = new StringBuilder("http://192.168.15.8:8066/TopCRMS/app/handSign");
        sb.append("?")
                .append("busFlowId").append("=").append("1306031978112715101583889785475").append("&")
                .append("nPageNo").append("=").append("2").append("&")
                .append("x0").append("=").append("243.31").append("&")
                .append("y0").append("=").append("332.305");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(buildUrlSta());
    }

    private static String buildUrlSta(){
        StringBuilder sb = new StringBuilder("http://192.168.15.8:8066/TopCRMS/app/handSign");
        sb.append("?")
                .append("busFlowId").append("=").append("1306031978112715101583889785475").append("&")
                .append("nPageNo").append("=").append("2").append("&")
                .append("x0").append("=").append("243.31").append("&")
                .append("y0").append("=").append("332.305");
        return sb.toString();
}}
