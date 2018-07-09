/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomatech.karim.utility;

import java.util.ArrayList;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 *
 * @author kolis
 */
public class OntologyPreprocess {

    private static OntDocumentManager mgr;
    private static OntModelSpec base_s;
    private static OntModel base;

    public static void main(String[] args) {
        openOntology();
    }

    public static void openOntology() {
        String ontFile = "C:\\Users\\os\\Documents\\NetBeansProjects\\WebAppOpe\\savedOwlOpe.owl";
        mgr = OntDocumentManager.getInstance();
        base_s = new OntModelSpec(OntModelSpec.OWL_MEM);
        base_s.setDocumentManager(mgr);
        base = ModelFactory.createOntologyModel(base_s);
        base.read("file:" + ontFile);
        ArrayList<String> conceptList = new ArrayList<>();
        ExtendedIterator<? extends OntClass> base_classes = base.listClasses();
        while (base_classes.hasNext()) {
            OntClass thisClass = base_classes.next();
            if (thisClass.getLocalName().equalsIgnoreCase("Dictionary")) {
                System.out.println("Found class: " + thisClass.getLocalName());
                ExtendedIterator<? extends OntClass> subClasses = thisClass.listSubClasses();
                while (subClasses.hasNext()) {
                    System.out.println("\tFound sub class: " 
                          //  + subClasses.next().getLocalName()
                    );
                    ExtendedIterator<? extends OntResource> instances = subClasses.next().listInstances();

                    while (instances.hasNext()) {
//                        Individual thisInstance = (Individual) instances.next();
//                        System.out.println("\tInstance: " + thisInstance.getLocalName());
                        ExtendedIterator<? extends Resource> sameAs = instances.next().listSameAs();
                        while (sameAs.hasNext()) {
                            System.out.println("Same as list: "+sameAs.next().getLocalName());
                        }
                    }
                }

            }
        }

    }
}
