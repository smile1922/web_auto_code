package framework.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GlobalAnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        // TODO Auto-generated method stub
        IRetryAnalyzer iRetryAnalyzer = annotation.getRetryAnalyzer();
        if (iRetryAnalyzer == null) {
            annotation.setRetryAnalyzer(RetryListener.class);
        }
    }
}