package org.grobid.trainer;

import org.grobid.core.GrobidModel;
import org.grobid.core.jni.WapitiModel;
import org.grobid.core.GrobidModels;
import org.grobid.trainer.SegmentationTrainer;
import java.math.BigDecimal;

import java.io.File;

/**
 * User: zholudev
 * Date: 3/20/14
 */
public class WapitiTrainer implements GenericTrainer {

    public static final String WAPITI = "wapiti";

	// default training parameters (only exploited by Wapiti)
	protected double epsilon = 0.00001; // default size of the interval for stopping criterion
	protected int window = 20; // default similar to CRF++
    protected int nbMaxIterations = 2000; // by default maximum of training iterations

    @Override
    public void train(File template, File trainingData, File outputModel, int numThreads, GrobidModel model) {
		System.out.println("epsilon: " + epsilon);
		System.out.println("window: " + window);
		System.out.println("nb threads: " + numThreads);
        WapitiModel.train(template, trainingData, outputModel, "--nthread " + numThreads +
//       		" --algo sgd-l1" +
			" -e " + BigDecimal.valueOf(epsilon).toPlainString() +
			" -w " + window +
			" -i " + nbMaxIterations
        );
    }

    @Override
    public String getName() {
        return WAPITI;
    }
	
    @Override
    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }
	
    @Override
    public void setWindow(int window) {
        this.window = window;
    }
	
    @Override
    public double getEpsilon() {
        return epsilon;
    }
	
    @Override
    public int getWindow() {
        return window;
    }

    @Override
    public void setNbMaxIterations(int interations) {
        this.nbMaxIterations = interations;
    }
    
    @Override
    public int getNbMaxIterations() {
        return nbMaxIterations;
    }
}
