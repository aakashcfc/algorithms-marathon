package SeamCarving.src;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Stencil {

	public static Triple[][] convolute3(Convolution<Pixel, Triple> conv, Pixel[][] input, int width, int height) {
		Triple[][] output = new Triple[width][height];
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				output[i][j] = conv.convolute(input, width, height, i, j);
			}
		}
		return output;
	}

	public static int[][] convolute1(Convolution<Pixel, Integer> conv, Pixel[][] input, int width, int height) {
		int[][] output = new int[width][height];
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				output[i][j] = conv.convolute(input, width, height, i, j);
			}
		}
		return output;
	}

	public static int[][] disruption(Pixel[][] input) {
		int width = input.length;
		int height  = input[0].length;

		int[][] gx_kernel =
			{{-1, 0, 1},
			 {-2, 0, 2},
			 {-1, 0, 1}};

		int[][] gy_kernel =
			{{-1, -2, -1},
			 { 0,  0, 0},
			 { 1,  2, 1}};

		BiFunction<Integer, Pixel, Function<Integer, Integer>> more =
			(coeff, c1) ->
				(res) ->
					(coeff * ((c1.red + c1.green + c1.blue)) / 3) + res;

		Supplier<Integer> done = () -> 0;

		Convolution<Pixel, Integer> conv_gx = new Convolution<>(gx_kernel,
																1,
																more,
																done);

		Convolution<Pixel, Integer> conv_gy = new Convolution<>(gy_kernel,
																1,
																more,
																done);

		int[][] D  = new int[width][height];
		int[][] gx = Stencil.convolute1(conv_gx, input, width, height);
		int[][] gy = Stencil.convolute1(conv_gy, input, width, height);

		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				D[i][j] = ((Double) Math.sqrt(Math.pow((double) gx[i][j], 2.0) + Math.pow((double) gy[i][j], 2.0))).intValue();
			}
		}

		return D;
	}

//	public static void main(String[] args) {
//		Pixel[][] img = new Pixel[16][16];
//		int       grey;
//		for (int y = 0; y < 16; ++y) {
//			for (int x = 0; x < 16; ++x) {
//				grey = x >= 8 ? 255 : 0;
//				img[x][y] = new Pixel(grey, grey, grey);
//			}
//		}
//		print_array(img, 16, 16);
//		System.out.println("");
//		print_array(disruption(img, 16, 16), 16, 16);
//	}
}