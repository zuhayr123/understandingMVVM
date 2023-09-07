package com.example.annotationprocessor;

import com.example.annotate.Builder;
import com.squareup.javapoet.*;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes("com.example.annotate.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class BuilderProcessor extends AbstractProcessor {

    private ProcessingEnvironment mProcessingEnvironment;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mProcessingEnvironment = processingEnvironment;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mProcessingEnvironment.getMessager().printMessage(Diagnostic.Kind.NOTE, "Builder Processing started ********");
        for (Element element : roundEnv.getElementsAnnotatedWith(Builder.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;
                try {
                    generateBuilder(typeElement);
                } catch (IOException e) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Error generating builder for " + typeElement.getSimpleName() + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private void generateBuilder(TypeElement typeElement) throws IOException {
        String className = typeElement.getSimpleName().toString();
        String builderClassName = className + "Builder";
        PackageElement packageElement = (PackageElement) typeElement.getEnclosingElement();
        String packageName = packageElement.getQualifiedName().toString();

        TypeSpec.Builder builderClass = TypeSpec.classBuilder(builderClassName)
                .addModifiers(Modifier.PUBLIC);

        for (Element enclosed : typeElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.FIELD) {
                VariableElement variableElement = (VariableElement) enclosed;
                String fieldName = variableElement.getSimpleName().toString();
                TypeMirror fieldType = variableElement.asType();

                // Generate setter method for each field
                MethodSpec setter = MethodSpec.methodBuilder("set" + capitalize(fieldName))
                        .addModifiers(Modifier.PUBLIC)
                        .returns(ClassName.get(packageName, builderClassName))
                        .addParameter(TypeName.get(fieldType), fieldName)
                        .addStatement("this.$N = $N", fieldName, fieldName)
                        .addStatement("return this")
                        .build();

                builderClass.addMethod(setter);
                builderClass.addField(TypeName.get(fieldType), fieldName, Modifier.PRIVATE);
            }
        }

        // Generate build() method
        MethodSpec buildMethod = MethodSpec.methodBuilder("build")
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get(packageName, className))
                .addStatement("return new $N(this.age, this.name)", className)
                .build();

        builderClass.addMethod(buildMethod);

        JavaFile javaFile = JavaFile.builder(packageName, builderClass.build()).build();
        javaFile.writeTo(processingEnv.getFiler());
    }

    private void generateConstructor(TypeSpec.Builder mainClass, TypeElement typeElement) {
        MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassName.get("", "Builder"), "builder");

        for (Element enclosed : typeElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.FIELD) {
                VariableElement variableElement = (VariableElement) enclosed;
                String fieldName = variableElement.getSimpleName().toString();
                constructor.addStatement("this.$N = builder.$N", fieldName, fieldName);
            }
        }

        mainClass.addMethod(constructor.build());
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}

