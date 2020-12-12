import java.io.*;
import java.lang.Math;
import java.util.*;

public class D {
    public static void main(String[] args) {
      int q=in.nextInt();
      ArrayList<Integer>res=new ArrayList<>();
      Trie trie=Trie.withZero();
      for(int i=0;i<q;++i){
        char c=in.next().toCharArray()[0];
        int x=in.nextInt();
        switch(c){
          case '+':
            Trie.add(trie,x);
            break;
          case '-':
            Trie.rem(trie,x);
            break;
          case '?':
            res.add(Trie.find(trie,x));
        }
      }
      out.println(res.toArray());
    }

    static class Trie {
      static Map<Integer,Integer>counts=new HashMap<>();

      Trie[] children;

      Trie() {
        this.children=new Trie[2];
      }

      static Trie withZero(){
        Trie t=new Trie();
        add(t,0);
        return t;
      }

      static void add(Trie t,int x){
        counts.put(x, counts.getOrDefault(x, 0)+1);
        int mask=1<<30;
        while(mask>0){
          if((x&mask)>0){
            if(t.children[1]!=null){
              t=t.children[1];
            }else{
              t.children[1]=new Trie();
              t=t.children[1];
            }
          }else{
            if(t.children[0]!=null){
              t=t.children[0];
            }else{
              t.children[0]=new Trie();
              t=t.children[0];
            }
          }
          mask>>=1;
        }
      }

      static int find(Trie t,int x){
        int mask=1<<30;
        int res=0;
        while(mask>0){
          if(t.children[1]==null){
            t=t.children[0];
          }else if(t.children[0]==null){
            t=t.children[1];
            res|=mask;
          }else{
            if((x&mask)>0){
              t=t.children[0];
            }else{
              t=t.children[1];
              res|=mask;
            }
          }
          mask>>=1;
        }
        //out.println(res + " " + (res^x));
        return res^x;
      }

      static void rem(Trie t,int x){
        counts.put(x, counts.getOrDefault(x, 0)-1);
        if(counts.get(x)!=0){
          return;
        }
        counts.remove(x);
        int mask=1<<30;
        Trie del=t;
        int deld=0;
        while(mask>0){
          if(t.children[0]!=null&&t.children[1]!=null){
            del=t;
            deld=(x&mask)>0?1:0;
          }
          if((x&mask)>0){
            t=t.children[1];
          }else{
            t=t.children[0];
          }
          mask>>=1;
        }
        del.children[deld]=null;
      }
    }

    static MyScanner in = new MyScanner();
    static MyPrintWriter out = new MyPrintWriter();

    static class MyPrintWriter {
      BufferedOutputStream bos;
      PrintWriter pw;

      MyPrintWriter() {
        this.bos = new BufferedOutputStream(System.out);
        this.pw = new PrintWriter(bos, true);
      }

      void print(String t) {
        pw.print(t);
      }

      void println() {
        pw.println("");
      }

      void println(String s) {
        pw.println(s);
      }

      void println(int t) {
        pw.println(t);
      }

      void println(long t) {
        pw.println(t);
      }

      void println(double t) {
        pw.println(t);
      }

      <T> void println(T t) {
        pw.println(t.toString());
      }

      void println(boolean[] ts) {
        println(ts, true);
      }

      void println(char[] ts) {
        println(ts, true);
      }

      void println(int[] ts) {
        println(ts, true);
      }

      void println(long[] ts) {
        println(ts, true);
      }

      void println(double[] ts) {
        println(ts, true);
      }

      <T> void println(T[] ts) {
        println(ts, true);
      }

      <T> void println(Collection<T> ts) {
        println(ts, true);
      }

      void println(boolean[] ts, boolean newline) {
        StringBuilder sb = new StringBuilder();
        for (boolean t: ts) {
          sb.append(t);
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      void println(char[] ts, boolean newline) {
        StringBuilder sb = new StringBuilder();
        for (char t: ts) {
          sb.append(t);
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      void println(int[] ts, boolean newline) {
        println(ts, newline, 0, ts.length-1);
      }

      void println(int[] ts, boolean newline, int imax) {
        println(ts, newline, 0, imax);
      }

      void println(int[] ts, boolean newline, int imin, int imax) {
        StringBuilder sb = new StringBuilder();
        for (int i = imin; i <= imax; ++i) {
          sb.append(ts[i]);
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      void println(long[] ts, boolean newline) {
        StringBuilder sb = new StringBuilder();
        for (long t: ts) {
          sb.append(t);
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      void println(double[] ts, boolean newline) {
        StringBuilder sb = new StringBuilder();
        for (double t: ts) {
          sb.append(t);
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      <T> void println(T[] ts, boolean newline) {
        println(Arrays.asList(ts), newline);
      }

      <T> void println(Collection<T> ts, boolean newline) {
        StringBuilder sb = new StringBuilder();
        for (T t: ts) {
          sb.append(t != null ? t.toString() : "null");
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      void println(int[][] tss) {
        StringBuilder sb = new StringBuilder();
        for (int[] ts: tss) {
          for (int t: ts) {
            sb.append(t).append(" ");
          }
          if (ts.length > 0) {
            sb.setCharAt(sb.length()-1, '\n');
          } else {
            sb.append('\n');
          }
        }
        pw.print(sb.toString());
        pw.flush();
      }

      void println(long[][] tss) {
        StringBuilder sb = new StringBuilder();
        for (long[] ts: tss) {
          for (long t: ts) {
            sb.append(t).append(" ");
          }
          if (ts.length > 0) {
            sb.setCharAt(sb.length()-1, '\n');
          } else {
            sb.append('\n');
          }
        }
        pw.print(sb.toString());
        pw.flush();
      }

      void println(double[][] tss) {
        StringBuilder sb = new StringBuilder();
        for (double[] ts: tss) {
          for (double t: ts) {
            sb.append(t).append(" ");
          }
          if (ts.length > 0) {
            sb.setCharAt(sb.length()-1, '\n');
          } else {
            sb.append('\n');
          }
        }
        pw.print(sb.toString());
        pw.flush();
      }
    }

    static class MyScanner {
      InputStreamReader is;
      BufferedReader br;
      StringTokenizer st;

      MyScanner() {
        this.is = new InputStreamReader(System.in);
        this.br = new BufferedReader(is);
      }

      void findToken() {
        while (st == null || !st.hasMoreTokens()) {
          try {
            st = new StringTokenizer(br.readLine());
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      }

      String next() {
        findToken();
        return st.nextToken();
      }

      String nextLine(){
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
      }

      int nextInt() {
        return Integer.parseInt(next());
      }

      int[] nextInts(int n) {
        return nextInts(n, false);
      }

      int[] nextInts(int n, boolean oneBased) {
        return nextInts(n, oneBased ? 1 : 0);
      }

      int[] nextInts(int n, int iStart) {
        int[] ints = new int[n+iStart];
        for (int i=iStart; i<ints.length; ++i) {
          ints[i] = nextInt();
        }
        return ints;
      }

      long nextLong() {
        return Long.parseLong(next());
      }

      long[] nextLongs(int n) {
        return nextLongs(n, false);
      }

      long[] nextLongs(int n, int iStart) {
        long[] longs = new long[n+iStart];
        for (int i=iStart; i<longs.length; ++i) {
          longs[i] = nextLong();
        }
        return longs;
      }

      long[] nextLongs(int n, boolean oneBased) {
        return nextLongs(n, oneBased ? 1 : 0);
      }

      double nextDouble() {
        return Double.parseDouble(next());
      }
    }
}