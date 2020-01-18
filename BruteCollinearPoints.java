/* *****************************************************************************
 *  Name: Arjoban Singh
 *  Date: jan 12, 2020
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private LineSegment[] totalSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
    {

        checkNull(points);
        checkDuplicate(points);

        Point[] clonedArray = points.clone();
        Arrays.sort(clonedArray);
        List<LineSegment> lineSegments = new ArrayList<>();
        for (int p = 0; p < clonedArray.length - 3; p++)
        {
            for (int q = p + 1; q < clonedArray.length - 2; q++)
            {
                for (int r = q + 1; r < clonedArray.length - 1; r++)
                {
                    for (int s = r + 1; s < clonedArray.length; s++)
                    {
                        if (clonedArray[p].slopeTo(clonedArray[q]) == clonedArray[p].slopeTo(clonedArray[r]) &&
                            clonedArray[p].slopeTo(clonedArray[q]) == clonedArray[p].slopeTo(clonedArray[s]))
                        {
                            lineSegments.add(new LineSegment(clonedArray[p], clonedArray[s]));
                        }
                    }
                }
            }
        }
        // totalSegments = (LineSegment[]) lineSegments.toArray();
        totalSegments = lineSegments.toArray(new LineSegment[lineSegments.size()]);

    }

    // the number of line segments
    public int numberOfSegments()
    {
        return totalSegments.length;
    }

    // the line segments
    public LineSegment[] segments()
    {
        return totalSegments.clone();
    }

    private void checkNull(Point[] points)
    {
        if (points == null) throw new IllegalArgumentException("Null array");
        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null) throw new IllegalArgumentException("Null element found");
        }
    }

    private void checkDuplicate(Point[] points)
    {
        for (int i = 0; i < points.length - 1; i++)
        {
            if (points[i].compareTo(points[i + 1]) == 0) throw new IllegalArgumentException("Duplicate found");
        }
    }

    public static void main(String[] args)
    {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }


}
