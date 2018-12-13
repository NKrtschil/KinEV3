package code;


import java.util.Date;

import edu.ufl.digitalworlds.j4k.DepthMap;
import edu.ufl.digitalworlds.j4k.J4KSDK;
import edu.ufl.digitalworlds.j4k.Skeleton;
import edu.ufl.digitalworlds.j4k.VideoFrame;

public class TestSkeleton extends J4KSDK {
  /*The following method will run every time a new depth frame is
  received from the Kinect sensor. The data of the depth frame is
  converted into a DepthMap object, with U,V texture mapping if
  available.*/
@Override
public void onDepthFrameEvent(short[] depth_frame, byte[] player_index, float[] XYZ, float[] UV) {
    
    DepthMap map=new DepthMap(getDepthWidth(),getDepthHeight(),XYZ);
    if(UV!=null) map.setUV(UV);
}

/*The following method will run every time a new skeleton frame
  is received from the Kinect sensor. The skeletons are converted
  into Skeleton objects.*/ 
@Override
public void onSkeletonFrameEvent(boolean[] skeleton_tracked, float[] joint_position, float[] joint_orientation, byte[] joint_status) {

    Skeleton skeletons[]=new Skeleton[getMaxNumberOfSkeletons()];
    for(int i=0;i<getMaxNumberOfSkeletons();i++)
      skeletons[i]=Skeleton.getSkeleton(i, skeleton_tracked, joint_position, joint_orientation, joint_status, this);        
}

/*The following method will run every time a new color frame
  is received from the Kinect video camera. The incoming frame
  is passed to the videoTexture object to update its content.*/    
@Override
public void onColorFrameEvent(byte[] data) {    

    videoTexture.update(getColorWidth(), getColorHeight(), data);
}

/*Similarly you can define methods that will run every time an
  infrared frame is received from the Kinect sensor.*/ 

public static void main(String[] args)
{
	
	if(System.getProperty("os.arch").toLowerCase().indexOf("64")<0)
	{
		System.out.println("WARNING: You are running a 32bit version of Java.");
		System.out.println("This may reduce significantly the performance of this application.");
		System.out.println("It is strongly adviced to exit this program and install a 64bit version of Java.\n");
	}
	
	System.out.println("This program will run for about 20 seconds.");
	TestSkeleton kinect=new TestSkeleton();
	kinect.start(J4KSDK.COLOR|J4KSDK.DEPTH|J4KSDK.SKELETON);
	
	
	//Sleep for 20 seconds.
	try {Thread.sleep(20000);} catch (InterruptedException e) {}
	
	
	kinect.stop();		
	//System.out.println("FPS: "+kinect.counter*1000.0/(new Date().getTime()-kinect.time));
}
} 


