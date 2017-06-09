/**
 * Created by dani9590 on 23/04/17.
 */
package Database;

import com.google.inject.Guice;
import com.google.inject.Injector;
import il.ac.technion.cs.sd.book.ext.LineStorage;
import il.ac.technion.cs.sd.book.ext.LineStorageFactory;
import il.ac.technion.cs.sd.book.ext.LineStorageModule;

import java.util.Arrays;
import java.util.Collection;

public class Reader{
    private LineStorage st = null;
    public Reader(LineStorageFactory st_factory, String filename) {
        st = st_factory.open(filename);
    }
    /*
    public Reader(LineStorage s){
        st = s;
    }*/
    public Reader(String filename){
        Injector injector = Guice.createInjector(new LineStorageModule());
        LineStorageFactory factory = injector.getInstance(LineStorageFactory.class);
        st = factory.open(filename);
    }
    public void insertStrings(Collection<String> stringsCollection) {
        String[] stringsArray = new String[stringsCollection.size()];
        stringsCollection.toArray(stringsArray);
        Arrays.sort(stringsArray);
        for (String curr_str : stringsArray) {
            st.appendLine(curr_str);
        }
    }

    public String find(String id, String delimiter, int index) throws InterruptedException {
        int last = st.numberOfLines() - 1, first = 0;
        while (last >= first) {
            int middle = first + (last - first) / 2;
            String[] keyValue = st.read(middle).split(delimiter);
            int compareResult = keyValue[0].compareTo(id);
            if (compareResult > 0)
                last = middle - 1;
            else
                first = middle + 1;
            if (compareResult == 0)
                return keyValue[index];
        }
        throw new InterruptedException();
    }
    //get the info if you know the line number
    public String find(int lineNum, String delimiter, int index) throws InterruptedException {
        if (lineNum > st.numberOfLines())
            throw new InterruptedException();
        String[] keyValue = st.read(lineNum).split(delimiter);
        return keyValue[index];

    }

    public int numberOfLines() throws InterruptedException{
        return st.numberOfLines();
    }

}
