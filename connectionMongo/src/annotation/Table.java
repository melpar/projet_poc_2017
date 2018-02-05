package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
// Annotation accessible Ã  l'execution

@Target(ElementType.TYPE)
// Annotation associÃ©e Ã  une classe



public @interface Table {

	String name();

}
