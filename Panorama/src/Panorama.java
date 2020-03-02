import java.io.*;
import java.util.*;

/**
 * NASA selects Dropbox as it‍‌‌‍‌‍‍‍‌‌‌‍‍‌‍‌‌‌‌s official partner, and we’re tasked with managing
 * a panorama for the universe. The Hubble telescope (or some other voyager we
 * have out there) will occasionally snap a photo of a sector of the universe,
 * and transmit it to us. You are to help write a data structure to manage this.
 * For the purpose of this problem, assume that the observable universe has been
 * divided into 2D sectors. Sectors are indexed by x- and y-coordinates.
 */
public class File {
    public File(String path) {}
    public Boolean exists() {}
    public byte[] read() {}
    public void write(bytes[] bytes) {}
}

public class Image {
    public Image(byte[] bytes) {}
        byte[] getBytes() {} // no more than 1MB in size
}

public class Sector {
    public Sector(int x, int y) {}
        int getX() {}
        int getY() {}
}

/**
 * row-major indexing to be consistent.
 */
public class Panorama {
    /**
     * initializes the data structure. rows x cols is the sector layout.
     * width, height can be as large as 1K each.
     */
    public Panorama(int rows, int cols) {}

    /**
     * The Hubble will occasionally call this (via some radio wave communication)
     * to report new imagery for the sector at (y, x)
     * Images can be up to 1MB in size.
     */
    public void update(int y, int x, Image image) {}

    /**
     * NASA will occasionally call this to check the view of a particular sector.
     */
    public Image fetch(int y, int x) {}

    /**
     * return the 2D index of the sector that has the stalest data.
     * the idea is that this may help the telescope decide where to aim next.
     */
    public Sector getStalestSector() {}
}