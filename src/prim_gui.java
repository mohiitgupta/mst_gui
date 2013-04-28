import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;


public class prim_gui {
	public static int[][] points = new int [20][20];
	public static int[][] cost = new int[20][20];
	public static int total = 0;
	public static JPanel graphPanel;
	public static boolean graphVisible = false;
	public static boolean mstVisible = false;
	public static int[] visited = new int[20];
	public static int current = 0;
	public static int[] min = new int[20];
	public static int[] from = new int[20];

	public static void main(String[] args){
		final JFrame window = new JFrame();
		window.setSize(400,400);
window.addWindowListener(new WindowListener(){
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
								
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e)
			{
				int confirm = JOptionPane.showOptionDialog(window,
		                "Are You Sure to Close this Application?",
		                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
		                JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(confirm==JOptionPane.YES_OPTION)
					System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		
		graphPanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				for(int i=0;i<total;i++){
					g.fillOval(points[i][0]-4, points[i][1]-4,8,8);
				}
				
				if(graphVisible){
					g.setColor(Color.WHITE);
					for(int i=0;i<total;i++)
						for(int j=0;j<total;j++)
							g.drawLine(points[i][0], points[i][1], points[j][0], points[j][1]);
				}
				
				if(mstVisible){
					g.setColor(Color.RED);
					for(int i=0;i<total;i++)
							g.drawLine(points[i][0], points[i][1], points[from[i]][0], points[from[i]][1]);
				}
			}
		};
		graphPanel.setLayout(null);
		graphPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent me) { 
				int x = me.getX();
				int y = me.getY();
				addpoint(x,y);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) { }

			@Override
			public void mouseExited(MouseEvent arg0) { }

			@Override
			public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseReleased(MouseEvent arg0) { }
			
		});
		
		window.add(graphPanel,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		
		JButton showGraph = new JButton("The Comlete Graph");
		buttonPanel.add(showGraph);
		showGraph.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (graphVisible)
					graphVisible = false;
				else 
					graphVisible = true;
				graphPanel.repaint();
			}
		});
		
		JButton findMst = new JButton("Click to view MST");
		buttonPanel.add(findMst);
		findMst.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (mstVisible)
					mstVisible = false;
				else{ 
					mstVisible = true;
					current =0;
					for(int i=0;i<total;i++){
						min[i] = Integer.MAX_VALUE;
						from[i] = 0;
						visited[i] = 0;
					}
					visited[current] = 1;
					min[current] = 0;
					from[current] = current;
				}
				process_prim();
				graphPanel.repaint();
			}
		});
		
		window.add(buttonPanel,BorderLayout.NORTH);
		window.setVisible(true);
	}
	
	public static void addpoint(int x, int y){
		for(int i=0;i<total;i++)
			cost[i][total]=cost[total][i]=
				(int) Math.sqrt(Math.pow((float)points[i][0]-(float)x,2) + Math.pow((float)points[i][1]-(float)y,2));
		
		points[total][0]=x;
		points[total++][1]=y;
		graphPanel.repaint();
	}
	
	public static void process_prim(){
		
		
		for(int k=0;k<total;k++){
			for(int j=0;j<total;j++){
				if(visited[j]==0 && cost[current][j]!=0){
					if(min[j] > cost[current][j]){
						min[j]=cost[current][j];
						from[j] = current;
					}
				}
			}
			int mincost = Integer.MAX_VALUE;
			for(int j=0;j<total;j++){
				if(visited[j]==0){
					if(min[j] < mincost){
						mincost=min[j];
						current = j;
					}
				}
			}
			visited[current] = 1;
		}
	}
}
