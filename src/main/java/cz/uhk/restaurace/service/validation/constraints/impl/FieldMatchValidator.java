package cz.uhk.restaurace.service.validation.constraints.impl;

import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.validation.constraints.FieldMatch;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by dann on 15.11.2014.
 * Class which encapsulates cross field validation e.g. password confirmation
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, User> {

    private String firstFieldName;
    private String secondFieldName;
    private String errorMessage;

    /**
     * Intialize validation fields and error message
     * @param constraintAnnotation
     */
    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        errorMessage = constraintAnnotation.errorMessage();
    }

    /**
     * Validate two fields, validation is successful if the fields match each other
     * @param value
     * @param cvc
     * @return
     */
    public boolean isValid(final User value, final ConstraintValidatorContext cvc){
        boolean toReturn = false;

        try{
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName );
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName );

            //System.out.println("firstObj = "+firstObj+"   secondObj = "+secondObj);

            toReturn = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception e){
            System.out.println(e.toString());
        }
        //If the validation failed
        if(!toReturn) {
            cvc.disableDefaultConstraintViolation();
            //In the initialiaze method you get the errorMessage: constraintAnnotation.message();
            cvc.buildConstraintViolationWithTemplate(errorMessage).addNode(firstFieldName).addConstraintViolation();
        }
        return toReturn;
    }
}
