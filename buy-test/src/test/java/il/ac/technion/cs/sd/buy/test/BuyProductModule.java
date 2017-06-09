package il.ac.technion.cs.sd.buy.test;

import com.google.inject.AbstractModule;

// This module is in the testing project, so that it could easily bind all dependencies from all levels.
public class BuyProductModule extends AbstractModule {
  @Override
  protected void configure() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
