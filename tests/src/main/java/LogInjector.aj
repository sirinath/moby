import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.SourceLocation;

public aspect LogInjector {
    private pointcut executionJoinPoints():
    	!within(LogInjector) && 
    	execution (* *.*(..));

    after() returning (Object result): executionJoinPoints() {
        SourceLocation sourceLocation = thisJoinPointStaticPart.getSourceLocation();
        CodeSignature signature = (CodeSignature) thisJoinPointStaticPart.getSignature();
        Class<?> type = sourceLocation.getWithinType();
        if (type == null)
          type = signature.getDeclaringType();
        Logger logger = LogManager.getLogger(type);
        logger.trace("Signature: {} | Args: {} | Result: {}", sig, Arrays.deepToString(thisJoinPoint.getArgs()), result);
      }

      after() throwing (Throwable error): executionJoinPoints() {
        SourceLocation sourceLocation = thisJoinPointStaticPart.getSourceLocation();
        CodeSignature signature = (CodeSignature) thisJoinPointStaticPart.getSignature();
        Class<?> type = sourceLocation.getWithinType();
        if (type == null)
          type = signature.getDeclaringType();
        Logger logger = LogManager.getLogger(type);
        logger.trace("Signature: {} | Args: {} | Result: {}", sig, Arrays.deepToString(thisJoinPoint.getArgs()), result);
      }
}