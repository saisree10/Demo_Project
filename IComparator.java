package DemoApp.Demo_Project;

import org.testng.annotations.Test;



	@Test
  public interface IComparator<X, Y> {
  public boolean compare(X x, Y y);
  
//  public Wrapper<X,Y>getData(File fil1, File file2);
	}

