
package de.cebitec.centrallookup;


import java.util.Collection;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;


/**
 * Class used to house anything one might want to store in a central lookup
 * which can affect anything within the application. It can be thought of as a
 * central context where any application data may be stored and watched.
 *
 * A singleton instance is created using @see getDefault(). This class is as
 * thread safe as Lookup. Lookup appears to be safe.
 * <p>
 * @author Wade Chandler
 * @version 1.0
 */
public class CentralLookup extends AbstractLookup {

    private static final long serialVersionUID = 8835897;
    private static CentralLookup def = new CentralLookup();
    private InstanceContent content = null;


    public CentralLookup( InstanceContent content ) {
        super( content );
        this.content = content;
    }


    public CentralLookup() {
        this( new InstanceContent() );
    }


    public void add( Object instance ) {
        content.add( instance );
    }


    public void remove( Object instance ) {
        content.remove( instance );
    }


    public <T> void removeAll( Class<T> clazz ) {
        Collection<? extends T> col = lookupAll( clazz );
        for( T o : col ) {
            remove( o );
        }
    }


    public static CentralLookup getDefault() {
        return def;
    }


}
