package test;

public class Test1 {
	  //地球平均半径  
    private static final double EARTH_RADIUS = 6378137;  
    //把经纬度转为度（°）  
    private static double rad(double d){  
       return d * Math.PI / 180.0;  
    }  
      
    /**  
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米  
     * @param lng1  
     * @param lat1  
     * @param lng2  
     * @param lat2  
     * @return  
     */  
    public static double getDistance(double lng1, double lat1, double lng2, double lat2){  
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
       double a = radLat1 - radLat2;  
       double b = rad(lng1) - rad(lng2);  
       double s = 2 * Math.asin(  
            Math.sqrt(  
                Math.pow(Math.sin(a/2),2)   
                + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)  
            )  
        );  
       s = s * EARTH_RADIUS;  
       s = Math.round(s * 10000) / 10000;  
       return s;  
    }  
      
      
    /**  
     * test  
     * @param args  
     */  
    public static void main(String[] args){  
    	System.out.println(rad(121.491909));
    	System.out.println(rad(31.233234));
    	System.out.println(rad(121.411994));
    	System.out.println(rad(31.206134));
        double distance = getDistance(23.182586,113.33418400000005,23.167595,113.35041899999999);  
        System.out.println("Distance is:"+distance);  
    }  
  
}
