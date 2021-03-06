package CommonUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BitUtil {
    public static void main (String args[]) throws IOException {
        int i=11111;
        byte[] bytes=null;
//        int to byte
        BigInteger integer=BigInteger.valueOf(i);
        bytes = integer.toByteArray();
        System.out.println(bytes.length);

//        int to byte
        ByteBuffer buffer=ByteBuffer.allocate(4);
        buffer.putInt(i);
        bytes = buffer.array();

//        int to byte
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(i);
        dos.flush();
        bytes = bos.toByteArray();

        //        byte to int
        buffer=ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.BIG_ENDIAN);
        System.out.println(buffer.getInt());

    }
    public static int[] byteArrayToIntArray(byte[] bytes){
        int[] value=new int[bytes.length/4];
        for (int i=0;i<bytes.length/4;i++){
            value[i]=byteToInt(new byte[]{
                    bytes[i*4],
                    bytes[i*4+1],
                    bytes[i*4+2],
                    bytes[i*4+3]
            });
        }
        return value;
    }
    public static byte[] intArrayToByteArray(int[] data){
        byte[] bytes=new byte[data.length*4];
        for (int i=0;i<data.length;i++){
            byte[] bytes1 = intToByte(i);
            System.arraycopy(bytes1,0,bytes,i*4,4);
        }
        return bytes;
    }
    public static int byteToInt(byte[] bytes,ByteOrder order){
        if (order==ByteOrder.BIG_ENDIAN){
            return byteToIntBig(bytes);
        }else{
            return byteToIntLittle(bytes);
        }
    }
    public static byte[] intToByte(int i,ByteOrder order){
        if (order.equals(ByteOrder.BIG_ENDIAN)){
            return intToBytesBig(i);
        }else{
            return intToBytesLittle(i);
        }
    }
    public static byte[] intToByte(int i){
        return intToByte(i,ByteOrder.nativeOrder());
    }
    public static int byteToInt(byte[] bytes){
        return byteToInt(bytes,ByteOrder.nativeOrder());
    }
    private static int byteToIntLittle(byte[] bytes){
        return (bytes[3]&0xff)<<24|
                (bytes[2]&0xff)<<16|
                (bytes[1]&0xff)<<8|
                (bytes[0]&0xff);
    }
    private static int byteToIntBig(byte[] bytes){
        return (bytes[0]&0xff<<24)|
                (bytes[1]&0xff<<16)|
                (bytes[2]&0xff<<8)|
                (bytes[3]&0xff);
    }
    private static byte[] i2b(int i){
        byte[] bytes=new byte[4];
        bytes[0]=(byte)((i>>24)&0xff);
        bytes[1]=(byte)((i>>16)&0xff);
        bytes[2]=(byte)((i>>8)&0xff);
        bytes[3]=(byte)(i&0xff);
        return bytes;
    }
    private static int b2i(byte[] bytes){

        int i=(bytes[0]&0xff)<<24;
        i+=(bytes[1]&0xff)<<24;
        i+=(bytes[2]&0xff)<<8;
        i+=(bytes[3]&0xff);
        return i;
    }
    private static byte[] intToBytesLittle(int i){
        byte[] bytes=new byte[4];
        bytes[3]=(byte)(i>>>24&0xff);
        bytes[2]=(byte)(i>>>16&0xff);
        bytes[1]=(byte)(i>>>8&0xff);
        bytes[0]=(byte)(i&0xff);
        return bytes;
    }
    private static byte[] intToBytesBig(int i){
        byte[] bytes=new byte[4];
        bytes[0]=(byte)(i>>>24&0xff);
        bytes[1]=(byte)(i>>>16&0xff);
        bytes[2]=(byte)(i>>>8&0xff);
        bytes[3]=(byte)(i&0xff);
        return bytes;
    }
    public static int setOne(int i,int index){
        return i|(1<<index);
    }
    public static int cleanZero(int i,int index){
        return i&~(1<<index);
    }
    public static int StringToBinary(String str){
        int r=0;
        char[] chars=str.toCharArray();
        for (int i=0;i<chars.length;i++) {
            char aChar=chars[i];
            if (aChar=='1'){
                r|=(1<<i);
            }
        }
        return r;
    }
}
