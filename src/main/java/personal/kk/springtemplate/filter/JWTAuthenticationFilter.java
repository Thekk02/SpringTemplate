package personal.kk.springtemplate.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import personal.kk.springtemplate.config.SpringSecurityConfig;
import personal.kk.springtemplate.security.JwtUtil;
import personal.kk.springtemplate.utils.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author kk
 * @description jwt验证过滤器
 * @date 2024-7-3 09:42:56
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = request.getRequestURI();

        String header = request.getHeader(JwtUtil.AUTHORIZATION);

        JSONObject json=new JSONObject();
        //跳过不需要验证的路径
        if(null != SpringSecurityConfig.AUTH_WHITELIST&& Arrays.asList(SpringSecurityConfig.AUTH_WHITELIST).contains(url)){
            chain.doFilter(request, response);
            return;

        }
        else if (StringUtils.isBlank(header)) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(new Result<>().setCode(400).setMsg("Token为空").setShowmsg(true)));
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request,response);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

        }catch (ExpiredJwtException e) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(new Result<>().setMsg("Token已过期").setCode(401).setShowmsg(true)));
            logger.error("Token已过期: {} " + e);
        } catch (UnsupportedJwtException e) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(new Result<>().setMsg("Token格式错误").setCode(402).setShowmsg(true)));
            logger.error("Token格式错误: {} " + e);
        } catch (MalformedJwtException e) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(new Result<>().setMsg("Token没有正确构造").setCode(403).setShowmsg(true)));
            logger.error("Token没有被正确构造: {} " + e);
        } catch (SignatureException e) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(new Result<>().setMsg("Token签名失败").setCode(405).setShowmsg(true)));
            logger.error("签名失败: {} " + e);
        } catch (IllegalArgumentException e) {
            //json.put("status", "-6");
            json.put("codeCheck", false);
            json.put("msg", "Token非法参数异常");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(new Result<>().setMsg("Token非法参数异常").setCode(406).setShowmsg(true)));
            logger.error("非法参数异常: {} " + e);
        }catch (Exception e){
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(new Result<>().setMsg("Invalid Token").setCode(407).setShowmsg(true)));
            logger.error("Invalid Token " + e.getMessage());
        }
    }



    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,HttpServletResponse response)  {
        String token = request.getHeader(JwtUtil.AUTHORIZATION);
        if (token != null) {
            String userName="";

            try {
                // 解密Token
                userName = JwtUtil.validateToken(token);
                if (StringUtils.isNotBlank(userName)) {
                    return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
                }
            }catch (ExpiredJwtException e) {
                throw e;
                //throw new TokenException("Token已过期");
            } catch (UnsupportedJwtException e) {
                throw e;
                //throw new TokenException("Token格式错误");
            } catch (MalformedJwtException e) {
                throw e;
                //throw new TokenException("Token没有被正确构造");
            } catch (SignatureException e) {
                throw e;
                //throw new TokenException("签名失败");
            } catch (IllegalArgumentException e) {
                throw e;
                //throw new TokenException("非法参数异常");
            }catch (Exception e){
                throw e;
                //throw new IllegalStateException("Invalid Token. "+e.getMessage());
            }
            return null;
        }
        return null;
    }

}
