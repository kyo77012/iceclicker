package service;

import java.nio.charset.Charset;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.sun.crypto.provider.SunJCE;

public class Cid {
  private static SecretKey secret_key;
  private static Cipher cipher;
  
  static {
    try {
      String encrypt_algorithm = "DESede";
      Cid.cipher = Cipher.getInstance( encrypt_algorithm );
      Provider sunjce = new SunJCE();
      Security.addProvider( sunjce );
      KeyGenerator keyGen = KeyGenerator.getInstance( encrypt_algorithm );
      Cid.secret_key = keyGen.generateKey();
    }
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
    } // catch
  } // static
  
  public static boolean CheckCid( String str_cid, String id ) {
    try {
      String[] str_cid_split = str_cid.split( "," );
      byte[] cid = new byte[str_cid_split.length];
      for ( int i = 0 ; i < str_cid_split.length ; i++ ) {
        cid[i] = ( byte ) Integer.parseInt( str_cid_split[i] );
      } // for
      Cid.cipher.init( Cipher.DECRYPT_MODE, Cid.secret_key );
      if ( id.equals( new String( Cid.cipher.doFinal( cid ) ) ) ) return true;
      else return false;
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
      return false;
    } // catch
  } // CheckCid()
  
  public static byte[] GetCid( String id ) {
    try {
      Cid.cipher.init( Cipher.ENCRYPT_MODE, Cid.secret_key );
      return Cid.cipher.doFinal( id.getBytes( Charset.forName( "UTF-8" ) ) );
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
      return null;
    } // catch
  } // GetCid()
} // class Cid
