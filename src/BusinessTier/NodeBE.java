/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessTier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Amanda Olson
 */
public class NodeBE
{
    List<EdgeBE> edgesToNode;     // list of all edges lead to this node
    List<EdgeBE> edgesFromNode;   // list of all edges lead from this node
    
    /**
    * Creates a default NodeBE object
    *     and list of edges to/from node initialized to empty
    */
    public NodeBE()
    {
        this.edgesToNode   = new ArrayList<>();
        this.edgesFromNode = new ArrayList<>();
    }

    /**
    * Creates a NodeBE object with 
    *     list of edges to/from node initialized to outArray[], inArray[]
    * @param inArray
    * @param outArray
    */
    public NodeBE(EdgeBE inArray[], EdgeBE outArray[])
    {
        this.edgesToNode   = new ArrayList<>(Arrays.asList(inArray));
        this.edgesFromNode = new ArrayList<>(Arrays.asList(inArray));
    }

    
}
