import java.lang.Math;
import java.util.*;

public class F
{
    private static class DSU {
      long area;
      boolean inside;
      int i;
      int j;
      long nCells;
      DSU parent;
      int rank;
      boolean visited;

      public DSU(long area, int i, int j) {
        this.area = area;
        this.inside = false;
        this.i = i;
        this.j = j;
        this.nCells = 1;
        this.parent = this;
        this.rank = 0;
        this.visited = false;
      }

      public static void union(DSU u, DSU v) {
        DSU rootU = DSU.find(u);
        DSU rootV = DSU.find(v);

        if (rootU != rootV) {
          if (rootU.rank < rootV.rank) {
            rootU.parent = rootV;
            rootV.area += rootU.area;
            rootV.nCells += rootU.nCells;
          } else if (rootU.rank > rootV.rank) {
            rootV.parent = rootU;
            rootU.area += rootV.area;
            rootU.nCells += rootV.nCells;
          } else {
            rootU.parent = rootV;
            rootV.area += rootU.area;
            rootV.nCells += rootU.nCells;
            rootV.rank++;
          }
        }
      }

      public static DSU find(DSU u) {
        if (u.parent != u) {
          u.parent = find(u.parent);
        }
        return u.parent;
      }
    }

    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          long n = input.nextLong();
          long m = input.nextLong();
          long k = input.nextLong();
          DSU[][] a = new DSU[(int)n][(int)m];
          int nSortedA = (int)(n*m);
          DSU[] sortedA = new DSU[nSortedA];
          for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
              DSU dsu = new DSU(input.nextLong(), i, j);
              a[i][j] = dsu;
              sortedA[(int)(i*m+j)] = dsu;
            }
          }
          input.close();

          Arrays.sort(sortedA, (DSU d, DSU e) -> Long.compare(e.area, d.area));
          for (int i = 0; i < nSortedA; i++) {
              sortedA[i].inside = true;
              long currentHeight = sortedA[i].area;
              int currentI = sortedA[i].i;
              int currentJ = sortedA[i].j;
              // System.out.println(currentI + " " + currentJ + " " + sortedA[i].area);

              if (currentI > 0 && a[currentI-1][currentJ].inside) {
                DSU.union(sortedA[i], a[currentI-1][currentJ]);
              }
              if (currentI < n-1 && a[currentI+1][currentJ].inside) {
                DSU.union(sortedA[i], a[currentI+1][currentJ]);
              }
              if (currentJ > 0 && a[currentI][currentJ-1].inside) {
                DSU.union(sortedA[i], a[currentI][currentJ-1]);
              }
              if (currentJ < m-1 && a[currentI][currentJ+1].inside) {
                DSU.union(sortedA[i], a[currentI][currentJ+1]);
              }

              DSU representative = DSU.find(sortedA[i]);
              long currentArea = representative.area;
              long currentCells = representative.nCells;

              // System.out.println(currentArea + " " + currentCells);

              if (currentCells*currentHeight >= k && k%currentHeight == 0) {
                long[][] solution = new long[(int)n][(int)m];
                long currentSum = 0;
                Stack<DSU> s = new Stack<DSU>();
                s.push(a[currentI][currentJ]);

                while (!s.empty() && currentSum < k) {
                  DSU top = s.pop();
                  int topI = top.i;
                  int topJ = top.j;

                  if (!top.visited && top.inside) {
                    // System.out.println(currentHeight + " " + topI + " " + topJ);
                    top.visited = true;
                    solution[(int)topI][(int)topJ] = currentHeight;
                    currentSum += currentHeight;

                    if (topI > 0) {
                      s.push(a[topI-1][topJ]);
                    }
                    if (topI < n-1) {
                      s.push(a[topI+1][topJ]);
                    }
                    if (topJ > 0) {
                      s.push(a[topI][topJ-1]);
                    }
                    if (topJ < m-1) {
                      s.push(a[topI][topJ+1]);
                    }
                  }
                }

                System.out.println("YES");
                StringBuilder sb = new StringBuilder();
                for (int ii=0; ii<n; ii++) {
                  for (int jj=0; jj<m; jj++) {
                    sb.append(solution[ii][jj] + " ");
                  }
                  sb.deleteCharAt(sb.length()-1);
                  sb.append("\n");
                }
                System.out.print(sb.toString());
                return;
              }
          }
          System.out.println("NO");
     }
}