package personal.kk.springtemplate.security;

import io.jsonwebtoken.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kk
 * @description jwt工具类
 * @date 2024-7-3 09:04:09
 */
public class JwtUtil {
    //过期时间
    private static final int EXPIRATION_TIME = 60*60*24;

    //自己设定的密钥,密钥盐自己生成长字符串
    private static final String SECRET = "q62Pq3yy7egg7IyWRSZq1uVwythwZkGx";

    //表头授权
    public static final String AUTHORIZATION = "Authorization";

    //创建token
    public static  String generateToken(String userName){
        Calendar calendar =  Calendar.getInstance();
        Date now = calendar.getTime();
        //设置签发时间
        calendar.setTime(new Date());
        //设置过期时间
        //添加秒钟
        calendar.add(Calendar.SECOND,EXPIRATION_TIME);
        Date time = calendar.getTime();
        HashMap<String,Object> map = new HashMap<>();
        //放入自己想要的验证信息进入map
        map.put("userName",userName);
        String jwt = Jwts.builder().setClaims(map).setIssuedAt(now).setExpiration(time).signWith(SignatureAlgorithm.HS256,SECRET).compact();

        //jwt一般会加前缀，这里没有加
        return jwt;
    }

    //解密token
    public static String validateToken(String token){
        try{
            //parse the token
            Map<String,Object> body = Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(token).getBody();
            String userName = body.get("userName").toString();
            return userName;
        }catch (ExpiredJwtException e) {
            throw e;
        } catch (UnsupportedJwtException e) {
            throw e;
        } catch (MalformedJwtException e) {
            throw e;
        } catch (SignatureException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
    }
}
