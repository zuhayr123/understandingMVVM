package com.example.annotationprocessor;

// MyAnnotationProcessor.java
import com.example.annotate.GenerateClass;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

@SupportedAnnotationTypes("com.example.annotate.GenerateClass")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class MyAnnotationProcessor extends AbstractProcessor {

    private ProcessingEnvironment mProcessingEnvironment;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mProcessingEnvironment = processingEnvironment;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            roundEnv.getElementsAnnotatedWith(annotation).forEach(element -> {
                mProcessingEnvironment.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing started ********");
                GenerateClass generateClassAnnotation = element.getAnnotation(GenerateClass.class);
                String className = generateClassAnnotation.name();
                generateClass(className);
            });
        }
        return true;
    }

    private void generateClass(String className) {
        try {
            String relativeClassName = "com.example.understandingmvvm."+className;
            mProcessingEnvironment.getMessager().printMessage(Diagnostic.Kind.NOTE, "Generate class called ******");
            JavaFileObject jfo = mProcessingEnvironment.getFiler().createSourceFile(relativeClassName);
            try (Writer writer = jfo.openWriter()) {
                writer.write("package com.example.understandingmvvm;\n\n");
                writer.write("public class " + className + " {\n\n");
                writer.write("    public void print() {\n");
                writer.write("        System.out.println(\"Hello from " + className + "!\");\n");
                writer.write("    }\n");
                writer.write("}\n");
            }
        } catch (Exception e) {
            mProcessingEnvironment.getMessager().printMessage(Diagnostic.Kind.NOTE, "Unable to write ******" + e.getMessage());
            e.printStackTrace();
        }
    }
}

