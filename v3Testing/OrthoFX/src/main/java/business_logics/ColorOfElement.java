
package business_logics;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ColorOfElement implements Nlp {
    @InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement"),@InputParam(name = "Jar Stream", type = "java.io.InputStream")})
    @ReturnType(name = "colorString", type = "java.lang.String")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          WebElement element = (WebElement) programElementsInput.get("Element");
          InputStream stream=(InputStream)programElementsInput.get("Jar Stream");
          String hex="";
          String color="";
          try {
          //  File screenshot = element.getScreenshotAs(OutputType.FILE);
            WebDriver driver = nlpRequestModel.getWebDriver();
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    String contentValue = (String) js.executeScript(
                            "var ele = arguments[0];" +
                            "var style = window.getComputedStyle(ele);" +
                            "return style.getPropertyValue('content');", 
                            element
                        );
                    log.info("The content form ELement is : "+contentValue);
	
                    String regex = "url\\(\"([^\"]*)\"\\)";
                    
                    // Compile the regex pattern
                    Pattern pattern = Pattern.compile(regex);
                    
                    // Create a matcher for the input string
                    Matcher matcher = pattern.matcher(contentValue);
                    String url = "";
                    // Find and print all matches
                    while (matcher.find()) {
                        // Extract the URL (group 1 in the pattern)
                         url = matcher.group(1);
                       // System.out.println("Extracted URL: " + url);
                    }	
                    
                    File tempFile = File.createTempFile("orthoteeth", ".png");
                    log.info("The url fetched form ELement is : "+url);
                   
                    jarCheck(url, tempFile.getAbsolutePath(), stream);
//                    File outputfile = new File("C:\\Users\\User\\Desktop\\mayankOrtho.png");
                    BufferedImage image = ImageIO.read(tempFile );
                    
//                    ImageIO.write(elementScreenshot, "png", outputfile);
                    int pixelColor = image.getRGB(image.getWidth()/2, image.getWidth()/2);
                    //int pixelColor = elementScreenshot.getRGB(20, 20);

                    // Extract the RGB values
                    int red = (pixelColor >> 16) & 0xFF;
                    int green = (pixelColor >> 8) & 0xFF;
                    int blue = pixelColor & 0xFF;
        		hex = String.format("#%02X%02X%02X", red, green, blue);
        		 color=getColorName(hex);
        		nlpResponseModel.setMessage("The Color is : "+color);
              nlpResponseModel.setStatus(CommonConstants.pass);
              tempFile.deleteOnExit();
              }
          catch(Exception e) {
        	  StringWriter sw = new StringWriter();
    			e.printStackTrace(new PrintWriter(sw));
    			String exceptionAsString = sw.toString();
              nlpResponseModel.setMessage("Failed to find color : "+color+" "+exceptionAsString);
              nlpResponseModel.setStatus(CommonConstants.fail);
              }

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("colorString", color);
          return nlpResponseModel;
      }

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

  /*    public static void convertSvgToPng(String svgUrl, String outputPath) throws TranscoderException, IOException {
          // Create a PNG Transcoder
          PNGTranscoder transcoder = new PNGTranscoder();
         log.info("SVG url is : "+svgUrl);
System.out.println("svg_url is : "+ svgUrl);
          // Create the TranscoderInput
          InputStream inputStream = new URL(svgUrl).openStream();
          TranscoderInput input = new TranscoderInput(inputStream);

          // Create the TranscoderOutput
          FileOutputStream outputStream = new FileOutputStream(outputPath);
          TranscoderOutput output = new TranscoderOutput(outputStream);

          // Convert the SVG to PNG
          transcoder.transcode(input, output);

          // Close the streams
          inputStream.close();
          outputStream.close();
      }*/
      
      
     public static String getColorName(String hex) throws IOException {
    	  String colorValue ="";
    	  hex=hex.replaceAll("#","");
    		URL url = new URL("https://www.thecolorapi.com/id?hex=" + hex);
  			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  			connection.setRequestMethod("GET");
  			int responseCode = connection.getResponseCode();
  			if (responseCode == HttpURLConnection.HTTP_OK) {
  				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  				String inputLine;
  				StringBuilder response = new StringBuilder();
  				while ((inputLine = in.readLine()) != null) {
  					response.append(inputLine);
  				}
  				in.close();
  				String jsonString = response.toString();
    	 Configuration configuration = Configuration.defaultConfiguration();
         ReadContext ctx = JsonPath.parse(jsonString);
          colorValue = ctx.read("$.name.value");
         System.out.println("Color Value is : "+colorValue);
         connection.disconnect();
    
     }
  			 return colorValue;
      
  } 
     public void jarCheck(String svgUrl,String pngPath,InputStream jarStream) throws IOException {
 		String fileName="SvgToPng";

 		 Path jarFilePath = Path.of(System.getProperty("user.dir"), fileName+".jar");
 	   boolean boo = Files.exists(jarFilePath);
 	   System.out.println( jarFilePath.getFileName());
 		System.out.println(boo);
 		if(boo==false) {
 			 // Path tempJarFile = Files.createTempFile(fileName, ".json");
 			  Files.createFile(Path.of(System.getProperty("user.dir"), fileName+".jar"));
 		}
 		else {}
 		
 	   Files.copy(jarStream, Path.of(System.getProperty("user.dir"), fileName+".jar"), StandardCopyOption.REPLACE_EXISTING);

 		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "java -Xmx16000m -jar "+jarFilePath+" "+svgUrl+" "+pngPath).directory(new File(System.getProperty("user.dir"))).redirectErrorStream(true);
 	   
 		Process process = processBuilder.start();

 	   try {
 	   	BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())) ;
 	       String line;
 	       while ((line = reader.readLine()) != null) {
 	           System.out.println(line);
 	       }
 	 

 	   // Wait for the process to finish
 	   int exitCode = process.waitFor();
 	   System.out.println("Command executed with exit code: " + exitCode);
 	} catch (Exception e) {
 	   e.printStackTrace();
 	}
 	}
}