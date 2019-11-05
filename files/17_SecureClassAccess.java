
package tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The purpose of this class is to provide a mechanism to bypass the java class
 * security features. It can be used to enable the testing of private methods
 * and private fields.This is especially useful when using JUNIT.
 *
 * @author David Heffernan
 * 
 */
public class SecureClassAccess {
 
    /**
     * <p>
     * Use this method to invoke a private method where you need to pass
     * parameters. If the method is not found it will search the superclasses
     * until it finds the method. If it doesn't find the method it will throw a
     * NoSuchMethodException
     * <p>
     * <b>Example: </b></br> You have a private method called
     * <code>addTwoNumbers()</code> that returns an int
     * <p>
     * First create an array of parameters for the two numbers:</br>
     * <p>
     * <code>
     * Object o[] = new Object[]{new Integer(5),new Integer(7)};
     * </code>
     * </p>
     * Invoke the method. If it is an int return type it will return Integer
     * object. You can follow the same pattern for all primitive types</br>
     * <p>
     * <code> 
     * MyClass myObject = new MyClass();
     *Integer addedNumbers = 
     *      (Integer)SecureClassAccess.invokeMethod(myObject,"addTwoNumbers",o);
     * </code>
     * </p>
     * Now you can convert to int primitive type if you need to:
     * <p>
     * <code>
     * addedNumbers.intValue();
     * </code></br> or</br><code>
     * assertEquals("This is not the correct value",7,addedNumbers.intValue());
     * </code>
     * </p>
     * 
     * @param object <code>Object</code> This is an instance of the object you
     *            are testing
     * @param name <code>String</code> the name of the method
     * @param args <code>Object[]</code> an array of args
     * @return Object You will need to cast this to the object you expect
     * @throws NoSuchMethodException if the method is not found
     * @throws InvocationTargetException wraps the Exception that the method
     *             being tested might throw
     */
    public static Object invokeMethod(
        Object object, 
        String name, 
        Object[] args) throws NoSuchMethodException, InvocationTargetException {
        
        Object o = new Object();
        List classList = new LinkedList();
        boolean bFound = false;
        
        Class c;
        try {
            c = object.getClass();
            classList.add(0,c);
            findSuperClasses(classList);
            Class myClass;
            
            Iterator i = classList.iterator();
            while(i.hasNext() && !bFound) {
                myClass = (Class)i.next();
                Method methods[] = myClass.getDeclaredMethods();
                // create a new object
                for (int x = 0; x < methods.length; x++) {
                    // The method we are test
                    if (methods[x].getName().equals(name)) {
                        // allow access to private methods
                        methods[x].setAccessible(true);
                        // define the parameters
                        o = methods[x].invoke(object, args);
                        bFound = true;
                    }
                }
            }
            
            // if the field wasn't found then throw an
            // NoSuchMethodException
            
            if (!bFound) {
                throw new NoSuchMethodException();
            }
           
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        return o;
    }
    
    /**
     * <p>
     * Use this method when you want to invoke a private method where there are
     * no parameters. If the method is not found it will search the superclasses
     * until it finds the method. If it doesn't find the method it will throw a
     * NoSuchMethodException 
     * </p>
     * <b>Example:</b></br> You have a private method called
     * <code>getStatus()</code> that returns a boolean
     * <p>
     * <code>
     * MyClass myObject = new MyClass();</br>
     * Boolean status = 
     *          (Boolean)SecureClassAccess.invokeMethod(myObject,"getStatus");
     * </code>
     * </p>
     * </code>
     * </p>
     * Now you can convert to booleab primitive type if you need to:
     * <p>
     * <code>status.booleanValue();</code></br> or</br><code>
     * assertTrue("The status should be true",status.booleanValue());</code>
     * </p>
     * 
     * @param object <code>Object</code> This is an instance of the object you
     *            are testing
     * @param name <code>String</code>
     * @return Object
     * @throws NoSuchMethodException if the method is not found
     * @throws InvocationTargetException wraps the Exception that the method
     *             being tested might throw
     */
    public static Object invokeMethod(
        Object object, 
        String name) throws NoSuchMethodException, InvocationTargetException{
        
        Object o = new Object();
        List classList = new LinkedList();
        boolean bFound = false;
        
        Class c;
        try {
            c = object.getClass();
            classList.add(0,c);
            findSuperClasses(classList);
            Class myClass;
            
            Iterator i = classList.iterator();
            while(i.hasNext() && !bFound){
                myClass = (Class)i.next();
                Method methods[] = myClass.getDeclaredMethods();
                // create a new object
                for (int x = 0; x < methods.length; x++) {
                    // The method we are test
                    if (methods[x].getName().equals(name)) {
                        // allow access to private methods
                        methods[x].setAccessible(true);
                        // define the parameters
                        o = methods[x].invoke(object, null);
                        bFound = true;
                    }
                }
                
                // if the field wasn't found then throw an
                // NoSuchMethodException
                
                if (!bFound) {
                    throw new NoSuchMethodException();
                }
            }
        }catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } 
        
        return o;
    }
    
    /**
     * <p>
     * This method will retrieve the value from a private declared field. If the
     * field is not found it will search the superclasses until it finds the
     * field. If it does not find the field it will throw a NoSuchFieldException.
     * </p>
     * <b>Example:</b></br> You have a private field called
     * <code>private String m_address</code> and you want to set it
     * <p>
     * <code>
     * MyClass myObject = new MyClass();</br>
     * String testAddress = 
     *          (String)SecureClassAccess.getField(myObject,"m_address");
     * </code>
     * </p>
     * 
     * @param object <code>Object</code> the instance of the object you are
     *            testing
     * @param name <code>String</code> the name of the field you want to get
     *            the value
     * @return Object you will need to cast this to the expected type
     * @throws NoSuchFieldException if the field is not found
     */
    public static Object getField(Object object,String name) 
    	throws NoSuchFieldException{
        
        Object o = new Object();
        Class c = null;
        
        List classList = new LinkedList();
        boolean bFound = false;
        
        try {
            c = object.getClass();
            classList.add(0,c);
            findSuperClasses(classList);    
            Class myClass;
            Iterator i = classList.iterator();
            while(i.hasNext() && !bFound){
                myClass = (Class)i.next();
                Field fields[] = myClass.getDeclaredFields();

                for (int x = 0; x < fields.length; x++) {
                    // The method we are test
                    if (fields[x].getName().equals(name)) {
                        // allow access to private fields
                        fields[x].setAccessible(true);
                        // get the value
                        o = fields[x].get(object);
                        bFound = true;
                    }
                }
            }
            
            // if the field wasn't found then throw an
            // NoSuchFieldException
            
            if (!bFound) {
                throw new NoSuchFieldException();
            }
            
        }catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        return o;
    }
    
    /**
     * <p>
     * Use this method to set the value on a privately declared member field. If
     * the field is not found in the class it searches all the superclasses
     * until it finds the field. If it does not find the field it will throw a
     * NoSuchFieldException.
     * </p>
     * <b>Example:</b></br> You have a private field called
     * <code>private String m_address</code>
     * <p>
     * <code>
     * MyClass myObject = new MyClass();</br>
     * String address = "Rue Buisseron";</br>
     * SecureClassAccess.setField(myObject,"m_address",address);
     * </code>
     * </p>
     * 
     * @param object <code>Object</code> the instance of the object you are
     *            testing
     * @param name <code>String</code> the name of the field
     * @param value <code>Object</code> the value you want to set
     * @throws NoSuchFieldException if the field is not found
     */
    public static void setField(Object object,String name,Object value)
    	throws NoSuchFieldException{
       
        List classList = new LinkedList();
        boolean bFound = false;
        Class c = null;
        
        try {
            c = object.getClass();
            classList.add(0,c);
            findSuperClasses(classList);
            Class myClass;
            
            Iterator i = classList.iterator();
            while (i.hasNext() && !bFound){
                myClass = (Class)i.next();
                Field fields[] = c.getDeclaredFields();
                // create a new object
                for (int x = 0; x < fields.length; x++) {
                    // The method we are test
                    if (fields[x].getName().equals(name)) {
                        // allow access to private fields
                        fields[x].setAccessible(true);
                        // get the value
                        fields[x].set(object, value);
                        bFound = true;
                    }
                }
            }
            
            // if the field wasn't found then throw an
            // NoSuchFieldException
            
            if (!bFound) {
                throw new NoSuchFieldException();
            }
            
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }    
    }
    
    /**
     * <p>
     * This method finds if the class has an superclasses. It fills the List
     * with all the superclasses found in order of the hierarchy.
     * </p>
     * @param classes <code>java.util.List</code> a List where the original
     *            class is first in the List. This method fills the List with
     *            any superclasses found.
     */
    private static void findSuperClasses(List classes){
        
        Class c = (Class)classes.get(0);
        
        boolean isFinished = false;
        
        // we are going to add the super class to the List
        int index = 1;
        Class foundClass;
        
        // repeat while we have superclasses
        while(!isFinished){
            foundClass = c.getSuperclass();
            
            if(foundClass != null){
                classes.add(index,foundClass);
                index++;
                // We now want to test if the superclass has a superclass
                c = foundClass;
            } else {
                isFinished = true;
            }
        }
    }
}