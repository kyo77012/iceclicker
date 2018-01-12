package service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.Response_utility.Response_type;


public class Response_utility {
  public static enum Response_type {
    OK,
    Error
  } // Response_type
  
  public static void Setup( HttpServletResponse response, Response_type response_type, Object data ) throws IOException {
    // response.addHeader( "Access-Control-Allow-Origin", "http://140.135.10.169:10377" );
    response.addHeader( "Access-Control-Allow-Methods", "GET, POST" );
    response.addHeader( "Content-Type", "application/json; charset=utf-8" );
    response.setCharacterEncoding( "UTF-8" );
    
    Response_content response_content = new Response_content( response_type, data );
    response.getWriter().print( ( new Gson() ).toJson( response_content ) );
  } // Setup()
} // class Response_utility


class Response_content {
  public String response_type;
  public String json_data;

  public Response_content( Response_type response_type, Object data ) {
    this.response_type = response_type.name();
    this.json_data = ( new Gson() ).toJson( ( data ) );
  } // Response_content()
} // class Error