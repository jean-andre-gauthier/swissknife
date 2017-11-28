[![Build Status](https://travis-ci.org/jean-andre-gauthier/swissknife.svg?branch=master)](https://travis-ci.org/jean-andre-gauthier/swissknife)

# swissknife

A collection of data structures and algorithms for programming contests written in Java. Therefore it is currently not organised as a library, but rather like a loose collection of helper methods/class that can be copy-pasted into a Java class to be submitted to an online judge.

*IMPORTANT NOTICE*: although the data structures have been thoroughly tested and used for submissions in various online judges, they are nevertheless not meant to be used in production environments (no argument validation, no bound checking, not implementing interfaces like Collection etc.). They'll work fine if you know what you are doing though.

## Algorithms

## Data Structures
* AA Tree: Variant of the Arne Andersson tree, where an additional integer is stored in each node, in order to be able to perform order-statistic queries. An integer may be inserted multiple times, which increases the occurrence counter in the node.

| Operation        | Complexity  | Description                                                                              |
| ---------------- | ----------- | ---------------------------------------------------------------------------------------- |
| `aaContains`     | `O(log(n))` | Checks whether the given integer exists in the tree                                      |
| `aaDelete`       | `O(log(n))` | Deletes the given integer from the tree                                                  |
| `aaDifference`   | `O(m+n)`    | Calculates the (multi)set difference between two AA trees (recursive implementation !)   |
| `aaInorder`      | `O(n)`      | Performs a Morris inorder tree traversal                                                 |
| `aaInsert`       | `O(log(n))` | Inserts an integer into the tree                                                         |
| `aaIntersection` | `O(m+n)`    | Calculates the (multi)set intersection between two AA trees (recursive implementation !) |
| `aaOccurrence`   | `O(log(n))` | Returns the occurrence count of the integer passed as parameter                          |
| `aaPostorder`    | `O(n)`      | Performs a Morris postorder tree traversal                                               |
| `aaPreorder`     | `O(n)`      | Performs a Morris preorder tree traversal                                                |
| `aaRank`         | `O(log(n))` | Calculates the rank of the integer, wrt to the values stored in the tree                 |
| `aaSelect`       | `O(log(n))` | Returns the node with the given rank                                                     |
| `aaUnion`        | `O(m+n)`    | Calculates the (multi)set union between two AA trees (recursive implementation !)        |

Tree internals: each node stores a tuple `(AANodeInt[] children, int level, int nNodes, int occurrence, int size, int value)`. Of those fields, only `children`, `level` and `value` are strictly necessary for an AATree. The other ones are used as bookkeeping values for order-statistics / tree traversal operations. While the the tree may store up to INTEGER.MAX_VALUE elements, you might want to use a B-Tree well before reaching this limit.

## IO Helpers
The IO class defines three helper streams that can be statically imported.

### `FastPrintWriter`
`FastPrintWriter` is a `BufferedOutputStream` that has been decorated with a `PrintWriter`. It provides the following overloaded `print` and `println` methods (a boolean argument for newlines may be provided where applicable):

| Type                     | Remarks                                                                                     |
| ------------------------ | ------------------------------------------------------------------------------------------- |
| `print(int)`             |                                                                                             |
| `print(long)`            |                                                                                             |
| `print(double)`          |                                                                                             |
| `print(T)`               | Calls the `toString` method defined on `T`                                                  |
| `println()`              | Prints an empty line                                                                        |
| `println(int)`           |                                                                                             |
| `println(long)`          |                                                                                             |
| `println(double)`        |                                                                                             |
| `println(T)`             | Calls the `toString` method defined on `T`                                                  |
| `println(int[])`         |                                                                                             |
| `println(long[])`        |                                                                                             |
| `println(double[])`      |                                                                                             |
| `println(T[])`           | Calls the `toString` method defined on `T`                                                  |
| `println(Collection<T>)` |                                                                                             |
| `println(Map<T, U>)`     | `key1 -> value1, ... , keyN -> valueN`                                                      |
| `println(int[][])`       | Prints the 2D-array contents in row-major order                                             |
| `println(long[][])`      | Prints the 2D-array contents in row-major order                                             |
| `println(double[][])`    | Prints the 2D-array contents in row-major order                                             |
| `println(T[][])`         | Prints the 2D-array contents in row-major order. Calls the `toString` method defined on `T` |

### `FastScanner`

| Type                                               | Remarks                                                           |
| -------------------------------------------------- | ----------------------------------------------------------------- |
| `int nextInt()`                                    | Reads the next `int` from `System.in`                             |
| `int[] nextInts(int n)`                            | Reads the n next `int`s from `System.in`                          |
| `int[] nextInts(int n, boolean oneBased)`          | Fills in the array starting from index 1 if `oneBased` is `true`  |
| `int[] nextInts(int n, int iMin)`                  | Fills in the returned array between `iMin` and `iMin+n`           |
| `long nextLong()`                                  |                                                                   |
| `long[] nextLongs(int n)`                          |                                                                   |
| `long[] nextLongs(int n, boolean oneBased)`        |                                                                   |
| `long[] nextLongs(int n, int iMin)`                |                                                                   |
| `double nextDouble()`                              |                                                                   |
| `double[] nextDoubles(int n)`                      |                                                                   |
| `double[] nextDoubles(int n, boolean oneBased)`    |                                                                   |
| `double[] nextDoubles(int n, int iMin)`            |                                                                   |

### `RandScanner`
This scanner defines methods that are similar to the ones from `FastScanner`. However, instead of reading from the System.in stream, random numbers are returned. This may be useful for stress-testing a solution.

## Submission Utilities
* `checksub [folder] [classname] [firsttestcase] [lasttestcase]`: compiles and runs the class against the given testcases (the file extension for testcase input files must be `.in`, and `.out` for sample output files).
* `maketests [folder] [classname] [firsttestcase] [lasttestcase]`: creates an empty .java file, along with empty testcase input / output files.

## Roadmap
* Add debug methods to IO Helpers
* Additional computational geometry algorithms
* Graph algorithms
* Binomial heaps
* Segment trees
* Network flows
* Splay trees
* Suffix trees / arrays
* Fast Fourier Transform
* Make a "real" library out of it?

## Contact
* Follow me on [![Github](http://i.imgur.com/9I6NRUm.png "Github")][https://github.com/jean-andre-gauthier]
* Follow me on [![Twitter](http://i.imgur.com/wWzX9uB.png "Twitter")][https://twitter.com/jandre_gauthier]
<!--- * Visit [jean-andre-gauthier.com][https://jean-andre-gauthier.com] --->
