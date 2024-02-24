package per.duyd.training.dsaa.seamcarving;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.princeton.cs.algs4.Picture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeamCarverTest {

  SeamCarver seamCarver;

  @BeforeEach
  void setUp() {
    seamCarver = new SeamCarver(new Picture("seamcarving/7x10.png"));
  }

  @Test
  public void shouldReturnCorrectHorizontalSeam() {
    int[] expected = new int[] {6, 7, 7, 7, 8, 8, 7};
    int[] actual = seamCarver.findHorizontalSeam();
    for (int i = 0; i < 7; i++) {
      assertEquals(expected[i], actual[i]);
    }
  }

  @Test
  public void shouldReturnCorrectVerticalSeam() {
    int[] expected = new int[] {2, 3, 4, 3, 4, 3, 3, 2, 2, 1};
    int[] actual = seamCarver.findVerticalSeam();
    for (int i = 0; i < 10; i++) {
      assertEquals(expected[i], actual[i]);
    }
  }

  @Test
  public void shouldRemoveHorizontalSeam() {
    seamCarver.removeHorizontalSeam(seamCarver.findHorizontalSeam());

    assertEquals(7, seamCarver.width());
    assertEquals(9, seamCarver.height());
  }

  @Test
  public void shouldRemoveVerticalSeam() {
    seamCarver.removeVerticalSeam(seamCarver.findVerticalSeam());

    assertEquals(6, seamCarver.width());
    assertEquals(10, seamCarver.height());
  }

  @Test
  public void shouldRemoveRandomSeam() {
    seamCarver = new SeamCarver(new Picture("seamcarving/4x6.png"));
    int[] verticalSeam = new int[] {1, 0, 1, 2, 3, 2};

    seamCarver.removeVerticalSeam(verticalSeam);
    Picture updatedPicture = seamCarver.picture();

    assertEquals(3, updatedPicture.width());
    assertEquals(6, updatedPicture.height());
  }

  @Test
  public void shouldThrowExceptionForInvalidSeam() {
    seamCarver = new SeamCarver(new Picture("seamcarving/7x10.png"));
    int[] invalidSeam = new int[] {0, 1, 0, 2, 1, 2, 1};

    assertThrows(IllegalArgumentException.class,
        () -> seamCarver.removeHorizontalSeam(invalidSeam));
  }

}