import com.baidu.aip.util.Base64Util;
import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.utils.FileUtil;
import com.baidu.ai.aip.utils.GsonUtils;
import java.util.*;

/**
* 人脸对比
*/
public class FaceMatch {
   /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static boolean match(String img,String face) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            //byte[] bytes1 = FileUtil.readFileByBytes(face1);
            byte[] bytes2 = FileUtil.readFileByBytes(face);
            //String image1 = Base64Util.encode(bytes1);
            String image2 = Base64Util.encode(bytes2);

            List<Map<String, Object>> images = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", img);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);
           
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            int c = result.indexOf("score");
            int sum=0;
            for(int i=7;i<9;i++) {
            	String d = result.substring(c+i, c+i+1);
            	for(int j=0;j<10;j++)
            		if(d.equals(j+"")) {
            			sum = 10*sum+j;
            			break;
            		}else {
            			continue;
            		}
            }
            System.out.println(sum);
			if(sum>80)
				return true;
			else
				return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}