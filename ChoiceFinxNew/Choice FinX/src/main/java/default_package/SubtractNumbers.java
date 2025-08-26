package default_package;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;

import java.math.BigDecimal;
import java.util.*;

public class SubtractNumbers implements Nlp {
    @InputParams({@InputParam(name = "operand 1", type = "java.math.BigDecimal"), @InputParam(name = "operand 2", type = "java.math.BigDecimal"), @InputParam(name = "Operator", type = "java.lang.String")})
    @ReturnType(name = "Output", type = "java.math.BigDecimal")
    public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

        Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
        BigDecimal number1 = (BigDecimal) programElementsInput.get("operand 1");
        BigDecimal number2 = (BigDecimal) programElementsInput.get("operand 2");
        String operator = (String) programElementsInput.get("Operator");

        BigDecimal operation = null;
        NlpResponseModel nlpResponseModel = new NlpResponseModel();

        try {
            if ("+".equals(operator)) {
                operation = number1.add(number2);
                System.out.println(operation);
                nlpResponseModel.setStatus(CommonConstants.pass);
                nlpResponseModel.setMessage("The Sum of " + number1 + " and " + number2 + " is " + operation);
            }
            if ("-".equals(operator)) {
                operation = number1.subtract(number2);
                System.out.println(operation);
                nlpResponseModel.setStatus(CommonConstants.pass);
                nlpResponseModel.setMessage("The difference of " + number1 + " and " + number2 + " is " + operation);
            }
        } catch (Exception e) {
            nlpResponseModel.setStatus(CommonConstants.fail);
            nlpResponseModel.setMessage("" + e);
        }

        nlpResponseModel.getAttributes().put("Output", operation);
        return nlpResponseModel;
    }

   
}
