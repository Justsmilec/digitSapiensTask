

class FileHandler{
    
    constructor(){

    }
    static readFile = () =>  {
        fetch('example.txt')
        .then(response => response.text())
        .then(text => console.log(text))
        
    }
}


class Node {
    constructor(data) {
      this.data = data;
      this.parent = null;
      this.children = [];
    }
  }


  class Tree {
    constructor(data) {
      let node = new Node(data);
      this._root = node;
    }
     
    //returns the node that has the given data, or null
    //use a depth-first search 
    find(data, node = this._root) {
      if (node.data == data)
          return node;

      for (let child of node.children) {
          if (this.find(data, child))
              return child;
      }   
      
      return null;
    }



    //create a new node with the given data and add it to the specified parent node
    add(data, parentData) {
      let node = new Node(data);
      let parent = this.find(parentData);

      if (parent) {
          parent.children.push(node);
          node.parent = parent;

          return node;
      }
      else {
          throw new Error(`Cannot add node: parent with data ${parentData} not found.`);
      }
    }



    //removes the node with the specified data from the tree
    remove(data) {
      let node = this.find(data)

      if (node) {
          let parent = node.parent;
          let indexOfNode = parent.children.indexOf(node);
          parent.children.splice(indexOfNode, 1);
      }
      else {
          throw new Error(`Cannot remove node: node with data ${data} not found.`);
      }
    }


    forEach(callback, node = this._root) {
      for (let child of node.children) {
          this.forEach(callback, child);
      }   
            callback(node);
    }

    //breadth-first tree traversal
        forEachBreadthFirst(callback) {
            //start with the root node
            let queue = [];
            queue.push(this._root);
        
            //while the queue is not empty
            while (queue.length > 0) {
                //take the next node from the queue  
                let node = queue.shift();
                
                //visit it
                callback(node);
        
                //and enqueue its children
                for (let child of node.children) {
                    queue.push(child);
                }
            }
        }
  }



class Test{
    constructor(){
        let t = new Tree("Adem");
        t.add("VP Finance", "Adem");
        t.add("VP Sales", "Adem");
        t.add("Salesperson", "VP Sales");
        t.add("Accountant", "VP Finance");
        t.add("Bookkeeper", "VP Finance");
        
        t.forEach(node => console.log(node.data));

        console.log("\n--------------\n");
        t.forEachBreadthFirst(node => console.log(node.data))


        console.log("\n\n-0---: ", FileHandler.readFile());
    }
    
}

var test = new Test();


