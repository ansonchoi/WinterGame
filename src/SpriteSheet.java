import java.awt.image.BufferedImage;

public  class SpriteSheet{
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage ss){
		this.image = image;
		System.out.println("Checked");
	}
	
	public BufferedImage grabImage(int col, int row,int width,int height){
		BufferedImage img;
		img = image.getSubimage((col*64)-64,(row*64)-64,width,height);
		return img;
	}
	
	public BufferedImage getBufferedImage(){
		if(image == null){System.out.println("null image");}
		return this.image;
	}

}