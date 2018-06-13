package com.example;

import org.apache.beam.sdk.transforms.Combine;
import org.apache.beam.sdk.transforms.Combine.CombineFn;

public class AverageFn extends CombineFn<Double, AverageFn.Accum, Double> {

	public static class Accum {
	    int sum = 0;
	    int count = 0;
	  }

	  @Override
	  public Accum createAccumulator() { return new Accum(); }

	  @Override
	  public Accum addInput(Accum accum, Double input) {
	      accum.sum += input;
	      accum.count++;
	      return accum;
	  }

	  @Override
	  public Accum mergeAccumulators(Iterable<Accum> accums) {
	    Accum merged = createAccumulator();
	    for (Accum accum : accums) {
	      merged.sum += accum.sum;
	      merged.count += accum.count;
	    }
	    return merged;
	  }

	  @Override
	  public Double extractOutput(Accum accum) {
	    return ((double) accum.sum) / accum.count;
	  }
}
