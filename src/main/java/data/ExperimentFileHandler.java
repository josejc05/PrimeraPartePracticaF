package data;

import model.BacteriaPopulation;
import model.Experiment;

import java.io.*;

public class ExperimentFileHandler {
    public static void saveExperiment(Experiment experiment, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(experiment);
        }
    }

    public static Experiment loadExperiment(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Experiment) in.readObject();
        }
    }

    public static void saveBacteriaPopulation(BacteriaPopulation bacteriaPopulation, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(bacteriaPopulation);
        }
    }
}