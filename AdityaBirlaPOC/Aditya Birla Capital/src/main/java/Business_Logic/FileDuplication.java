package Business_Logic;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FFmpegFrameGrabber;
//import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
 

import org.springframework.stereotype.Component;

@Component("LIC19774_PJT1002_PE_NLPff85a69c-4ee0-46bf-ae5b-4ad25d69a482")
public class FileDuplication implements Nlp {
	@InputParams({ @InputParam(name = "Video File Path", type = "java.lang.String") })
	@Override
	public List<String> getTestParameters() throws NlpException {
		List<String> params = new ArrayList<>();
		return params;
	}

	@Override
	public StringBuilder getTestCode() throws NlpException {
		StringBuilder sb = new StringBuilder();
		return sb;
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
		System.out.println("executing the PE");
		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		//Loader.load(avutil.class);
		String	inputStream	=(String)attributes.get("Video File Path");
		try {
			String outputFile = System.getProperty("user.dir")+"\\"+"outputout.y4m";
			Path filePath = Paths.get(inputStream);

			if (Files.exists(filePath)) {
				FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream);
				System.out.println("starting grabber");
				grabber.start();
				System.out.println("started grabber");
				FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, grabber.getImageWidth(), grabber.getImageHeight());
				recorder.setFormat("yuv4mpegpipe");
				System.out.println("starting recorder");
				recorder.setPixelFormat(0);
				recorder.start();
				System.out.println("started recorder");
				while (true) {
					var frame = grabber.grabImage();
					if (frame == null) break;

					recorder.record(frame);
				}
				System.out.println("stopping grabber");
				grabber.stop();
				System.out.println("stopped grabber");
				System.out.println("stopping recorder");
				recorder.stop();
				System.out.println("stopped recorder");
			}	    
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Video File converted to Y4M and stored to local");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed To handle File");
		}
		return nlpResponseModel;
	}
	/*
	public static void main(String[] args) throws org.bytedeco.javacv.FFmpegFrameGrabber.Exception, org.bytedeco.javacv.FFmpegFrameRecorder.Exception {
		  String outputFile = System.getProperty("user.dir")+"\\"+"outputout.y4m";
    	  Path filePath = Paths.get(outputFile);
System.out.println("The path is "+outputFile);
    	
    		  FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("C:\\Users\\User\\Downloads\\Adhaar front page.mp4");
              grabber.start();

              FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("C:\\Users\\User\\Downloads\\nayana.y4m", grabber.getImageWidth(), grabber.getImageHeight());
              recorder.setFormat("yuv4mpegpipe");
        
              recorder.setPixelFormat(0);
              recorder.start();

              while (true) {
                  var frame = grabber.grabImage();
                  if (frame == null) break;

                  recorder.record(frame);
              }
              grabber.stop();
              recorder.stop();
	
} 
	*/
}