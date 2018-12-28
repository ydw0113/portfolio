
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TetrisBoard extends JPanel implements Runnable, KeyListener, MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;

	private Tetris tetris;
	private GameClient client;
	Image a;
	public static final int BLOCK_SIZE = 30;
	public static final int BOARD_X = 180;
	public static final int BOARD_Y = 75;
	private int minX = 1, minY = 0, maxX = 10, maxY = 25, down = 50, up = 0;
	private final int MESSAGE_X = 2;
	private final int MESSAGE_WIDTH = BLOCK_SIZE * (7 + minX);
	private final int MESSAGE_HEIGHT = BLOCK_SIZE * (6 + minY);
	private final int PANEL_WIDTH = maxX * BLOCK_SIZE + MESSAGE_WIDTH + BOARD_X;
	private final int PANEL_HEIGHT = 21 * BLOCK_SIZE + MESSAGE_HEIGHT + BOARD_Y;
	private JButton btnStart = new JButton("시작하기");
	private JButton btnExit = new JButton("나가기");
	private JButton btntet = new JButton("테트리스");
	private JButton btnpuyo = new JButton("뿌요뿌요");
	private String ip;
	private int port;
	private String nickName;
	private Thread th;
	private ArrayList<Block> blockList;
	private ArrayList<TetrisBlock> nextBlocks;
	private TetrisBlock shap;
	private TetrisBlock ghost;
	private TetrisBlock hold;
	private Block[][] map;
	private TetrisController controller;
	private TetrisController controllerGhost;
	private boolean isPlay = false;
	private boolean isHold = false;
	private boolean usingGhost = true;
	private boolean usingGrid = true;
	private int removeLineCount = 0;
	private int removeLineCombo = 0;
	private int flag = 2;
	protected static final int S_LOGO = 1;
	protected static final int S_FADEOUT = 2;
	protected static final int S_GAME = 3;

	protected static final int FADEOUTN = 31;

	//
	// Game Board Image

	Image imgBack;
	Image puyoPics[];
	Image Sa[];
	protected Image imgFadeIn[];
	protected int m_imgFadeOutN;
	private ImageIcon icon = new ImageIcon("images/1.png");
	private Image img = icon.getImage(); // 이미지 객체
	private ImageIcon icon2 = new ImageIcon("images/2.png");
	private Image img2 = icon2.getImage(); // 이미지 객체
	private ImageIcon icon3 = new ImageIcon("images/3.png");
	private Image img3 = icon3.getImage(); // 이미지 객체
	// Game Board
	int nwTile, nhTile;
	int nwpuyoBox, nhpuyoBox;
	int nwBack, nhBack, nwFront, nhFront;

	int m_imgBoard[][] = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, } };
	boolean b_imgBoard[][] = { { true, true, true, true, true, true }, { true, true, true, true, true, true },
			{ true, true, true, true, true, true }, { true, true, true, true, true, true },
			{ true, true, true, true, true, true }, { true, true, true, true, true, true },
			{ true, true, true, true, true, true } };
	int m_spareImg[] = new int[m_sparecnt * DROPCNT];

	int m_spareCnt;
	int m_dropCnt;

	//
	// Board Space
	int m_box_sx;
	int m_box_sy;
	int m_spacex = 400;
	int m_spacey = 200;

	//
	// Display Strings
	final String strTitle = "Puyo Puyo";
	private String strLevel = "Level-0";
	private String strLeftTime = "LeftTime:20";
	int g_status;

	boolean qStop = false; // Stop Game
	boolean bNext = true; // Level Up
	boolean bEnd; // Game in Level

	// Game Board
	static final int XSIZE = 6;
	static final int YSIZE = 12;
	static final int PUYOSIZE = 4;
	static final int DROPCNT = 2;
	static final int REMSIZE = 4; // The number of elements to remove more than or equal to
	static final int UP = 1;
	static final int LEFT = 2;
	static final int DOWN = 3;
	static final int RIGHT = 4;
	static final int MATCH = 4;
	static final int DIMEN = 2;

	//
	// Time for a game (milli) (ms unit)
	protected static final int TIME_LIMIT = 60000;
	protected static final int LEVEL_TIME_LIMIT = 30000;
	protected static final int PUYO_INTERVAL = 1000;
	protected static final float SPEED_DEC = 0.8f;
	private volatile int m_nSleep = 10;

	//
	// Next Board
	int m_imgBoardnext[][] = new int[YSIZE][XSIZE];
	boolean b_imgBoardnext[][] = new boolean[YSIZE][XSIZE];
	boolean b_searchBoard[][] = new boolean[YSIZE][XSIZE];
	int m_searchNext[][] = new int[YSIZE * XSIZE][DIMEN];
	int m_searchCnt;

	int sxcnt = (int) XSIZE / 2;
	int sycnt = 0;
	static final int m_sparecnt = 2;
	// int m_spareImg2[] = new int[m_sparecnt * DROPCNT];
	int m_curImg[] = new int[DROPCNT];
	int m_curDir;
	int m_bottomlimit[] = new int[XSIZE];
	int m_bottommin = YSIZE - 1, m_bottommax = YSIZE - 1;
	int m_deletecnt[] = new int[XSIZE];

	Random r1 = new Random(System.currentTimeMillis());

	//
	// Keyboard Status Variables
	private volatile boolean m_bLeftKey;
	private volatile boolean m_bRightKey;
	private volatile boolean m_bUpKey;
	private volatile boolean m_bDownKey;
	private volatile boolean m_bSpaceKey;
	private volatile boolean m_bEscKey;
	private volatile boolean m_bRKey;
	private int c = 0;
	//
	// Mouse Status Variables
	private volatile boolean m_bMouseClick = false;
	private volatile int m_nMouseX;
	private volatile int m_nMouseY;

	public TetrisBoard(Tetris tetris, GameClient client) {
		this.tetris = tetris;
		this.client = client;
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));// 기본크기
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setLayout(null);
		this.setFocusable(true);

		btnStart.setBounds(PANEL_WIDTH - BLOCK_SIZE * 6, PANEL_HEIGHT - 100, BLOCK_SIZE * 6, 50);
		btnStart.setFocusable(false);
		btnStart.setEnabled(false);
		btnStart.addActionListener(this);
		btnExit.setBounds(PANEL_WIDTH - BLOCK_SIZE * 6, PANEL_HEIGHT - 50, BLOCK_SIZE * 6, 50);
		btnExit.setFocusable(false);
		btnExit.addActionListener(this);
		btntet.setBounds(0, PANEL_HEIGHT - 170, BLOCK_SIZE * 6, 30);
		btntet.setFocusable(false);
		btntet.addActionListener(this);
		btnpuyo.setBounds(0, PANEL_HEIGHT - 140, BLOCK_SIZE * 6, 30);
		btnpuyo.setFocusable(false);
		btnpuyo.addActionListener(this);

		// addKeyListener(new KeyListener());

		this.add(btnStart);
		this.add(btnExit);
		this.add(btntet);
		this.add(btnpuyo);

		try {
			initGame();

			nwTile = puyoPics[0].getWidth(this);
			nhTile = puyoPics[0].getHeight(this);
			nwpuyoBox = 6 * nwTile;
			nhpuyoBox = 12 * nhTile;
			nwBack = nwFront = 6 * nwTile + m_spacex;
			nhBack = nhFront = 12 * nhTile + m_spacey;

		} catch (Exception except) {
		}

	}

	public void startNetworking(String ip, int port, String nickName) {
		this.ip = ip;
		this.port = port;
		this.nickName = nickName;
		this.repaint();
	}

	/**
	 * TODO : 게임시작 게임을 시작한다.
	 */

	public void gameStart() {
		if (flag == 0) {
			// 돌고 있을 스레드를 정지시킨다.
			if (th != null) {
				try {
					isPlay = false;
					th.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 맵셋팅
			map = new Block[maxY][maxX];
			blockList = new ArrayList<Block>();
			nextBlocks = new ArrayList<TetrisBlock>();

			// 도형셋팅
			shap = getRandomTetrisBlock();
			ghost = getBlockClone(shap, true);
			hold = null;
			isHold = false;
			controller = new TetrisController(shap, maxX - 1, maxY - 1, map);
			controllerGhost = new TetrisController(ghost, maxX - 1, maxY - 1, map);
			this.showGhost();
			for (int i = 0; i < 5; i++) {
				nextBlocks.add(getRandomTetrisBlock());
			}
		}

		try {
			Random r1 = new Random();
			initGameFrame();
			for (int i = 0; i < m_sparecnt * DROPCNT; i++)
				m_spareImg[i] = (int) (r1.nextDouble() * 4);

			for (int i = 0, y = 0; i < m_curImg.length; i++) {
				m_curImg[i] = (int) (r1.nextDouble() * 4);
				m_searchNext[i][0] = sxcnt;
				m_searchNext[i][1] = y--;

				if ((m_searchNext[i][1] > -1)) {
					m_imgBoardnext[m_searchNext[i][1]][sxcnt] = m_curImg[i];
					b_imgBoardnext[m_searchNext[i][1]][sxcnt] = true;
				}
			}
		} catch (Exception e) {
		}

		// 스레드 셋팅
		isPlay = true;

		th = new Thread(this);
		th.start();

	}

	private Image loadImage(MediaTracker mt, String strFile) {
		// To use MediaTracker to load images dynamically
		Image img = Toolkit.getDefaultToolkit().getImage(strFile);
		mt.addImage(img, 0);

		return img;
	}

	// TODO : paint
	protected void paintComponent(Graphics g) {

		g.clearRect(0, 0, this.getWidth(), this.getHeight() + 1);

		g.setColor(new Color(0, 87, 102));

		// g.fillRect(0, 0, (maxX+minX+13)*BLOCK_SIZE+1, BOARD_Y);

		// g.setColor(new Color(92,209,229));
		MediaTracker mt = new MediaTracker(this);
		a = loadImage(mt, "images/b.jpg");
		// String Sc[] = {"1.png","2.png","3.png"};

		g.drawImage(a, 0, 0, this);
		// g.fillRect(0, BOARD_Y, (maxX+minX+13)*BLOCK_SIZE+1, maxY*BLOCK_SIZE+1);
		g.setColor(Color.black);

		// IP 출력
		g.drawString("ip : " + ip + "     port : " + port, 20, 500);

		// NickName 출력
		g.drawString("닉네임 : " + nickName, 20, 520);

		// 속도
		Font font = g.getFont();

		Color color = new Color(0, 0, 0, 150);
		g.setColor(color);


		g.fillRect(BOARD_X + BLOCK_SIZE * minX, 195, maxX * BLOCK_SIZE + 1, 21 * BLOCK_SIZE + 1);
		g.fillRect(BLOCK_SIZE * minX, 195, BLOCK_SIZE * 5, BLOCK_SIZE * 5);
		g.fillRect(BOARD_X + BLOCK_SIZE * minX + (maxX + 1) * BLOCK_SIZE + 1, 195, BLOCK_SIZE * 5, BLOCK_SIZE * 5);
		g.fillRect(BOARD_X + BLOCK_SIZE * minX + (maxX + 1) * BLOCK_SIZE + 1, 195 + BLOCK_SIZE * 7, BLOCK_SIZE * 5,
				BLOCK_SIZE * 12);

		// HOLD NEXT 출력
		g.setFont(new Font(font.getFontName(), font.getStyle(), 20));
		Color color2 = new Color(255, 255, 255, 200);
		g.setColor(color2);
		g.drawString("H O L D", BLOCK_SIZE + 12, BOARD_Y + BLOCK_SIZE + BLOCK_SIZE * 3 + 20);
		g.drawString("N E X T", BOARD_X + BLOCK_SIZE + (maxX + 1) * BLOCK_SIZE + 1 + 12,
				BOARD_Y + BLOCK_SIZE + BLOCK_SIZE * 3 + 20);
		g.setFont(font);

		// 그리드 표시
		if (usingGrid) {
			g.setColor(Color.darkGray);
			for (int i = 1; i < 21; i++)
				g.drawLine(BOARD_X + BLOCK_SIZE * minX, BOARD_Y + BLOCK_SIZE * (i + 4),
						BOARD_X + (maxX + minX) * BLOCK_SIZE, BOARD_Y + BLOCK_SIZE * (i + 4));
			for (int i = 1; i < maxX; i++)
				g.drawLine(BOARD_X + BLOCK_SIZE * (i + minX), BOARD_Y + BLOCK_SIZE * 4,
						BOARD_X + BLOCK_SIZE * (i + minX), BOARD_Y + BLOCK_SIZE * (4 + 21));
		}
		if (c == 1) {
			g.drawImage(img, 250, 300, this);
		}
		if (c == 2) {
			g.drawImage(img2, 250, 300, this);
		}
		//if (c == 3) {
			g.drawImage(img3, 250, 300, this);
		//}
		if (flag == 0) {
			int x = 0, y = 0, newY = 0;
			if (hold != null) {
				x = 0;
				y = 0;
				newY = 3;
				x = hold.getPosX();
				y = hold.getPosY();
				hold.setPosX(-4 + minX);
				hold.setPosY(6);
				hold.drawBlock(g);
				hold.setPosX(x);
				hold.setPosY(y);
			}

			if (nextBlocks != null) {
				x = 0;
				y = 0;
				newY = 6;
				for (int i = 0; i < nextBlocks.size(); i++) {
					TetrisBlock block = nextBlocks.get(i);
					x = block.getPosX();
					y = block.getPosY();
					block.setPosX(13 + minX);
					block.setPosY(newY + minY);
					if (newY == 6)
						newY = 9;
					block.drawBlock(g);
					block.setPosX(x);
					block.setPosY(y);
					newY += 3;
				}
			}

			if (blockList != null) {
				x = 0;
				y = 0;
				for (int i = 0; i < blockList.size(); i++) {
					Block block = blockList.get(i);
					x = block.getPosGridX();
					y = block.getPosGridY();
					block.setPosGridX(x + minX);
					block.setPosGridY(y + minY);
					block.drawColorBlock(g);
					block.setPosGridX(x);
					block.setPosGridY(y);
				}
			}

			if (ghost != null) {

				if (usingGhost) {
					x = 0;
					y = 0;
					x = ghost.getPosX();
					y = ghost.getPosY();
					ghost.setPosX(x + minX);
					ghost.setPosY(y + minY);
					ghost.drawBlock(g);
					ghost.setPosX(x);
					ghost.setPosY(y);
				}
			}

			if (shap != null) {
				x = 0;
				y = 0;
				x = shap.getPosX();
				y = shap.getPosY();
				shap.setPosX(x + minX);
				shap.setPosY(y + minY);
				shap.drawBlock(g);
				shap.setPosX(x);
				shap.setPosY(y);
			}

		}

		if (flag == 1) {

			try {
				int nwFront, nhFront;

				nwFront = getSize().width;
				nhFront = getSize().height;

				if ((imgBack == null) || (nwBack != nwFront) || (nhBack != nhFront)) {
					imgBack = createImage(nwFront, nhFront);
					g = imgBack.getGraphics();
					nwBack = nwFront;
					nhBack = nhFront;
				}

				/* draw BackBuffer to Front Buffer */
				// To clear screen
				g.setColor(Color.white);
				g.clearRect(0, 0, nwFront, nhFront);

				//
				// Puyo Puyo Game Display
				//
				int imgX = 230;
				int imgY = 195;
				m_box_sx = imgX;
				m_box_sy = imgY;
				a = loadImage(mt, "images/b.jpg");

				g.drawImage(a, 0, 0, this);
				g.setColor(Color.black);
				// g.drawString(strLevel, m_box_sx, m_box_sy + nhpuyoBox + 20);
				g.drawString(strLeftTime, m_box_sx + nwpuyoBox - 70, m_box_sy + nhpuyoBox + 20);

				// gBack.drawRect(m_box_sx, m_box_sy, nwpuyoBox, nhpuyoBox);
				// gBack.drawRect(m_box_sx-nwTile-30, m_box_sy, nwTile,
				// (m_dropCnt*m_spareCnt)*nhTile);

				int m_Cnt = m_dropCnt * m_spareCnt;
				int m_spareBoxXM = m_box_sx - nwTile / 2 - 30;
				int m_startXM = (int) (m_box_sx + nwTile * 3.5f);
				g.setColor(color);
				g.fillRect(m_box_sx - nwTile - 50, m_box_sy, nwTile + 40, (m_dropCnt * m_spareCnt) * nhTile + 30);
				for (int i = m_dropCnt * m_spareCnt - 1, j = 0; i >= 0; i--, j++) {
					g.drawImage(puyoPics[m_spareImg[i]], m_box_sx - nwTile - 30, m_box_sy + j * nhTile, this);
				}

				g.fillRect(BOARD_X + BLOCK_SIZE * minX + 20, 195, maxX * BLOCK_SIZE - 30, 18 * BLOCK_SIZE + 1);

				for (int y = 0; y < m_imgBoard.length; y++) {
					for (int x = 0; x < m_imgBoard[0].length; x++) {
						if (b_imgBoard[y][x] == true)
							g.drawImage(puyoPics[m_imgBoard[y][x]], m_box_sx + x * nwTile, m_box_sy + y * nhTile, this);

					}
				}

				// g.drawRect(m_box_sx, m_box_sy, nwpuyoBox, nhpuyoBox);

				// g.drawImage(imgBack, 0, 0, this);
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void run() {
		int countMove = (21 - 1) * 5;
		int countDown = 0;
		int countUp = up;

		if (flag == 0) {
			while (isPlay) {
				try {
					Thread.sleep(10);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (countDown != 0) {
					countDown--;
					if (countDown == 0) {

						if (controller != null && !controller.moveDown())
							this.fixingTetrisBlock();
					}
					this.repaint();
					continue;
				}

				countMove--;
				if (countMove == 0) {
					countMove = (21 - 1) * 5;
					if (controller != null && !controller.moveDown())
						countDown = down;
					else
						this.showGhost();
				}

				if (countUp != 0) {
					countUp--;
					if (countUp == 0) {
						countUp = up;
						addBlockLine(1);
					}
				}

				this.repaint();

			}
		} // while()
		else if (flag == 1) {
			// Thread thisThread = Thread.currentThread();
			while (isPlay) {

				try {
					/*
					 * if (bNext == false) { initGameFrame(); }
					 */
					// Thread.sleep(20);
					processGame(th);
					client.gameover();
					break;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}// run()

	/**
	 * 맵(보이기, 논리)을 상하로 이동한다. lineNumber num -1 or 1
	 */
	public void dropBoard(int lineNumber, int num) {

		// 맵을 떨어트린다.
		this.dropMap(lineNumber, num);

		// 좌표바꿔주기(1만큼증가)
		this.changeTetrisBlockLine(lineNumber, num);

		// 다시 체크하기
		this.checkMap();

		// 고스트 다시 뿌리기
		this.showGhost();
	}

	/**
	 * lineNumber의 위쪽 라인들을 모두 num칸씩 내린다. lineNumber num 칸수 -1,1
	 */
	private void dropMap(int lineNumber, int num) {
		if (num == 1) {
			// 한줄씩 내리기
			for (int i = lineNumber; i > 0; i--) {
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = map[i - 1][j];
				}
			}

			// 맨 윗줄은 null로 만들기
			for (int j = 0; j < map[0].length; j++) {
				map[0][j] = null;
			}
		} else if (num == -1) {
			// 한줄씩 올리기
			for (int i = 1; i <= lineNumber; i++) {
				for (int j = 0; j < map[i].length; j++) {
					map[i - 1][j] = map[i][j];
				}
			}

			// removeLine은 null로 만들기
			for (int j = 0; j < map[0].length; j++) {
				map[lineNumber][j] = null;
			}
		}
	}

	/**
	 * lineNumber의 위쪽 라인들을 모두 num만큼 이동시킨다. lineNumber num 이동할 라인
	 */
	private void changeTetrisBlockLine(int lineNumber, int num) {
		int y = 0, posY = 0;
		for (int i = 0; i < blockList.size(); i++) {
			y = blockList.get(i).getY();
			posY = blockList.get(i).getPosGridY();
			if (y <= lineNumber)
				blockList.get(i).setPosGridY(posY + num);
		}
	}

	/**
	 * 테트리스 블럭을 고정시킨다.
	 */
	private void fixingTetrisBlock() {
		synchronized (this) {
			if (stop) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		boolean isCombo = false;
		removeLineCount = 0;

		// drawList 추가
		for (Block block : shap.getBlock()) {
			blockList.add(block);
		}

		// check
		isCombo = checkMap();

		if (isCombo)
			removeLineCombo++;
		else {
			removeLineCombo = 0;
			c = 0;
		}

		// 콜백메소드
		this.getFixBlockCallBack(blockList, removeLineCombo, removeLineCount);

		// 다음 테트리스 블럭을 가져온다.
		this.nextTetrisBlock();

		// 홀드가능상태로 만들어준다.
		isHold = false;
	}// fixingTetrisBlock()

	/**
	 * 
	 * true-지우기성공, false-지우기실패
	 */
	private boolean checkMap() {
		boolean isCombo = false;
		int count = 0;
		Block mainBlock;

		for (int i = 0; i < blockList.size(); i++) {
			mainBlock = blockList.get(i);

			// map에 추가
			if (mainBlock.getY() < 0 || mainBlock.getY() >= maxY)
				continue;

			if (mainBlock.getY() < maxY && mainBlock.getX() < maxX)
				map[mainBlock.getY()][mainBlock.getX()] = mainBlock;

			// 줄이 꽉 찼을 경우. 게임을 종료한다.
			if (mainBlock.getY() == 5 && mainBlock.getX() > 2 && mainBlock.getX() < 7) {
				this.gameEndCallBack();
				break;
			}

			// 1줄개수 체크
			count = 0;
			for (int j = 0; j < maxX; j++) {
				if (map[mainBlock.getY()][j] != null)
					count++;

			}

			// block의 해당 line을 지운다.
			if (count == maxX) {
				removeLineCount++;
				this.removeBlockLine(mainBlock.getY());
				isCombo = true;
			}
		}
		return isCombo;
	}

	/**
	 * 테트리스 블럭 리스트에서 테트리스 블럭을 받아온다.
	 */
	public void nextTetrisBlock() {
		shap = nextBlocks.get(0);
		this.initController();
		nextBlocks.remove(0);
		nextBlocks.add(getRandomTetrisBlock());
	}

	private void initController() {
		controller.setBlock(shap);
		ghost = getBlockClone(shap, true);
		controllerGhost.setBlock(ghost);
	}

	/**
	 * lineNumber 라인을 삭제하고, drawlist에서 제거하고, map을 아래로 내린다. lineNumber 삭제라인
	 */
	private void removeBlockLine(int lineNumber) {
		// 1줄을 지워줌
		for (int j = 0; j < maxX; j++) {
			for (int s = 0; s < blockList.size(); s++) {
				Block b = blockList.get(s);
				if (b == map[lineNumber][j])
					blockList.remove(s);
			}
			map[lineNumber][j] = null;
		} // for(j)

		this.dropBoard(lineNumber, 1);
	}

	/**
	 * TODO : 게임종료콜벡 게임이 종료되면 실행되는 메소드
	 */
	public void gameEndCallBack() {
		client.gameover();
		if (flag == 0)
			this.isPlay = false;
		if (flag == 1)
			this.qStop = true;
	}

	/**
	 * 고스트블럭을 보여준다.
	 */
	private void showGhost() {
		ghost = getBlockClone(shap, true);
		controllerGhost.setBlock(ghost);
		controllerGhost.moveQuickDown(shap.getPosY(), true);
	}

	/**
	 * 랜덤으로 테트리스 블럭을 생성하고 반환한다. 테트리스 블럭
	 */
	public TetrisBlock getRandomTetrisBlock() {
		switch ((int) (Math.random() * 7)) {
		case TetrisBlock.TYPE_CENTERUP:
			return new CenterUp(4, 1);
		case TetrisBlock.TYPE_LEFTTWOUP:
			return new LeftTwoUp(4, 1);
		case TetrisBlock.TYPE_LEFTUP:
			return new LeftUp(4, 1);
		case TetrisBlock.TYPE_RIGHTTWOUP:
			return new RightTwoUp(4, 1);
		case TetrisBlock.TYPE_RIGHTUP:
			return new RightUp(4, 1);
		case TetrisBlock.TYPE_LINE:
			return new Line(4, 1);
		case TetrisBlock.TYPE_NEMO:
			return new Nemo(4, 1);
		}
		return null;
	}

	/**
	 * tetrisBlock과 같은 모양으로 고스트의 블럭모양을 반환한다. tetrisBlock 고스트의 블럭모양을 결정할 블럭 고스트의
	 * 블럭모양을 반환
	 */
	public TetrisBlock getBlockClone(TetrisBlock tetrisBlock, boolean isGhost) {
		TetrisBlock blocks = null;
		switch (tetrisBlock.getType()) {
		case TetrisBlock.TYPE_CENTERUP:
			blocks = new CenterUp(4, 1);
			break;
		case TetrisBlock.TYPE_LEFTTWOUP:
			blocks = new LeftTwoUp(4, 1);
			break;
		case TetrisBlock.TYPE_LEFTUP:
			blocks = new LeftUp(4, 1);
			break;
		case TetrisBlock.TYPE_RIGHTTWOUP:
			blocks = new RightTwoUp(4, 1);
			break;
		case TetrisBlock.TYPE_RIGHTUP:
			blocks = new RightUp(4, 1);
			break;
		case TetrisBlock.TYPE_LINE:
			blocks = new Line(4, 1);
			break;
		case TetrisBlock.TYPE_NEMO:
			blocks = new Nemo(4, 1);
			break;
		}
		if (blocks != null && isGhost) {
			blocks.setGhostView(isGhost);
			blocks.setPosX(tetrisBlock.getPosX());
			blocks.setPosY(tetrisBlock.getPosY());
			blocks.rotation(tetrisBlock.getRotationIndex());
		}
		return blocks;
	}

	/**
	 * TODO : 콜백메소드 테트리스 블럭이 고정될 때 자동 호출 된다. removeCombo 현재 콤보 수 removeMaxLine 한번에
	 * 지운 줄수
	 */
	public void getFixBlockCallBack(ArrayList<Block> blockList, int removeCombo, int removeMaxLine) {
		if (removeCombo < 3) {
			if (removeMaxLine == 2) {
				client.addBlock(1);
				c = 1;
				System.out.println(c);
			} else if (removeMaxLine == 3) {
				client.addBlock(3);
				c = 2;
				System.out.println(c);
			} else if (removeMaxLine == 4) {
				client.addBlock(4);
				c = 3;
				System.out.println(c);
			}
		} else if (removeCombo < 10) {
			if (removeMaxLine == 3) {
				client.addBlock(2);
				c = 1;
				System.out.println(c);
			} else if (removeMaxLine == 4) {
				client.addBlock(4);
				c = 2;
				System.out.println(c);
			} else {
				client.addBlock(1);
				c = 1;
			}
		} else {
			if (removeMaxLine == 3) {
				client.addBlock(3);
				c = 2;
				System.out.println(c);
				;
			} else if (removeMaxLine == 4) {
				client.addBlock(5);
				c = 3;
				System.out.println(c);
			} else {
				client.addBlock(2);
				c = 1;
				System.out.println(c);
			}
		}
	}

	/**
	 * 블럭을 홀드시킨다.
	 */
	public void playBlockHold() {
		if (isHold)
			return;

		if (hold == null) {
			hold = getBlockClone(shap, false);
			this.nextTetrisBlock();
		} else {
			TetrisBlock tmp = getBlockClone(shap, false);
			shap = getBlockClone(hold, false);
			hold = getBlockClone(tmp, false);
			this.initController();
		}

		isHold = true;
	}

	/**
	 * 가장 밑에 줄에 블럭을 생성한다. numOfLine
	 */
	boolean stop = false;

	public void addBlockLine(int numOfLine) {
		stop = true;
		// 내리기가 있을 때까지 대기한다.
		// 내리기를 모두 실행한 후 다시 시작한다.
		Block block;
		int rand = (int) (Math.random() * maxX);
		for (int i = 0; i < numOfLine; i++) {
			this.dropBoard(maxY - 1, -1);
			for (int col = 0; col < maxX; col++) {
				if (col != rand) {
					block = new Block(0, 0, Color.GRAY, Color.GRAY);
					block.setPosGridXY(col, maxY - 1);
					blockList.add(block);
					map[maxY - 1][col] = block;
				}
			}
			// 만약 내려오는 블럭과 겹치면 블럭을 위로 올린다.
			boolean up = false;
			for (int j = 0; j < shap.getBlock().length; j++) {
				Block sBlock = shap.getBlock(j);
				if (map[sBlock.getY()][sBlock.getX()] != null) {
					up = true;
					break;
				}
			}
			if (up) {
				controller.moveDown(-1);
			}
		}

		this.showGhost();
		this.repaint();
		synchronized (this) {
			stop = false;
			this.notify();
		}
	}

	public void keyReleased(KeyEvent e) {
		if (flag == 1) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_UP)
				m_bUpKey = false;
			else if (key == KeyEvent.VK_DOWN)
				m_bDownKey = false;
			else if (key == KeyEvent.VK_LEFT)
				m_bLeftKey = false;
			else if (key == KeyEvent.VK_RIGHT)
				m_bRightKey = false;
			else if (key == KeyEvent.VK_ESCAPE)
				m_bEscKey = false;
			else if (key == KeyEvent.VK_R)
				m_bRKey = false;

		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (flag == 0) {
			if (!isPlay)
				return;
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				controller.moveLeft();
				controllerGhost.moveLeft();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				controller.moveRight();
				controllerGhost.moveRight();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				controller.moveDown();
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				controller.nextRotationLeft();
				controllerGhost.nextRotationLeft();
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				controller.moveQuickDown(shap.getPosY(), true);
				this.fixingTetrisBlock();
			} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				playBlockHold();
			}
			this.showGhost();
			this.repaint();
		}
		if (flag == 1) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_UP)
				m_bUpKey = true;
			else if (key == KeyEvent.VK_DOWN)
				m_bDownKey = true;
			else if (key == KeyEvent.VK_LEFT)
				m_bLeftKey = true;

			else if (key == KeyEvent.VK_RIGHT)
				m_bRightKey = true;
			else if (key == KeyEvent.VK_SPACE)
				m_bSpaceKey = true;
			else if (key == KeyEvent.VK_ESCAPE) {
				m_bEscKey = true;

				System.exit(0);
			} else if (key == KeyEvent.VK_R)
				m_bRKey = true;

			if (bNext)
				checkKey();
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		this.requestFocus();
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			if (client != null) {

				client.gameStart();

			} else {
				this.gameStart();
			}
		} else if (e.getSource() == btnExit) {
			if (client != null) {
				if (tetris.isNetwork()) {
					client.closeNetwork(tetris.isServer());
				}
			} else {
				System.exit(0);
			}

		}
		if (e.getSource() == btntet) {
			flag = 0;
			isPlay = true;
		}
		if (e.getSource() == btnpuyo) {
			flag = 1;
			isPlay = true;
		}
	}

	public boolean isPlay() {
		return isPlay;
	}

	public void setPlay(boolean isPlay, boolean qStop) {
		this.isPlay = isPlay;
		this.qStop = qStop;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public void setClient(GameClient client) {
		this.client = client;
	}

	public GameClient getClient() {
		return client;
	}

	private void initGame() throws Exception {
		String puyoSrc[] = { "puyo_red.png", "puyo_green.png", "puyo_blue.png", "puyo_yellow.png" };

		try {
			puyoPics = new Image[puyoSrc.length];

			MediaTracker mt = new MediaTracker(this);

			for (int i = 0; i < puyoSrc.length; i++) {
				puyoPics[i] = loadImage(mt, "images/" + puyoSrc[i]);
			}
			/*
			 * for (int i = 0; i < Sc.length; i++) { Sa[i] = loadImage(mt, "images/" +
			 * Sc[i]); }
			 */

			mt.waitForAll();
			mt = null;

		} catch (InterruptedException except) {
			if (puyoPics != null) {
				for (int i = 0; i < puyoSrc.length; i++) {
					puyoPics[i] = null;
				}
				puyoPics = null;
			}
		}

	}

	public void destroy() {
		destroyGame();
	}

	private void destroyGame() {

		m_imgBoard = null;
		imgBack = null;
		puyoPics = null;
		m_imgBoard = null;
		b_imgBoard = null;

		if (puyoPics != null) {
			for (int i = 0; i < puyoPics.length; i++)
				puyoPics[i] = null;
			puyoPics = null;
		}

		if (imgFadeIn != null) {
			for (int i = 0; i < FADEOUTN; ++i)
				imgFadeIn[i] = null;
			imgFadeIn = null;
		}
	}

	public void updateFrame(int mimgBoardnext[][], boolean bimgBoardnext[][], int nLevel, int nLeftTime,
			int mspareImg[], int mspareCnt, int mdropCnt) {
		m_imgBoard = mimgBoardnext;
		b_imgBoard = bimgBoardnext;
		strLevel = "Level-" + nLevel;
		strLeftTime = "Left Time: " + nLeftTime;
		m_spareImg = mspareImg;
		m_spareCnt = mspareCnt;
		m_dropCnt = mdropCnt;
		repaint();

	}

	private void processGame(Thread thisThread) throws Exception {

		int nLevel = 1;
		bNext = true;

		while (bNext && thisThread == th) {

			bNext = processStage(thisThread);
			nLevel += 1;
		}

	}

	private boolean processStage(Thread thisThread) throws Exception {
		long tickIn = 0;
		long tickOut;
		long tickNow;

		long tickElapsed = 0;
		bEnd = false;
		int nLeftTime = (int) LEVEL_TIME_LIMIT / 1000;
		int puyoInterval = PUYO_INTERVAL;

		for (int i = 1; i < 5; i++)
			puyoInterval = (int) (puyoInterval * 1.1);

		try {
			long tickPlay = System.currentTimeMillis();
			long puyoTick = System.currentTimeMillis();

			while ((qStop == false) && (bEnd == false)) {

				tickIn = System.currentTimeMillis();
				tickNow = tickIn;
				if (System.currentTimeMillis() - puyoTick > puyoInterval) {

					changePuyoPos(0, 1);
					puyoTick = System.currentTimeMillis();
				}

				this.updateFrame(m_imgBoardnext, b_imgBoardnext, 2, nLeftTime, m_spareImg, m_sparecnt, DROPCNT);
				tickOut = System.currentTimeMillis();
				tickElapsed = System.currentTimeMillis() - tickIn;

				th.sleep(m_nSleep);

				nLeftTime = (int) (LEVEL_TIME_LIMIT - tickOut + tickPlay) / 1000;
				if (nLeftTime <= 0)
					bEnd = true;

				if ((m_curDir == UP) || (m_curDir == DOWN)) {

					for (int i = 0; i < DROPCNT; i++)
						if (m_bottomlimit[m_searchNext[i][0]] <= 0)
							return false;
				}
			}

		} catch (Exception e) {
		}
		return true;
	}

	public int testCollision(int xinc, int yinc) {
		int t_X, t_Y;
		for (int i = 0; i < m_curImg.length; i++) {
			t_X = m_searchNext[i][0] + xinc;
			t_Y = m_searchNext[i][1] + yinc;

			if ((t_X < 0) || (t_X >= XSIZE))
				return 1;

			if ((m_bottomlimit[t_X] < t_Y) || (t_Y >= YSIZE))
				return 2;
		}

		return 3;
	}

	public boolean testCollisionShape(int pushDir) {
		if (pushDir == UP) {
			if (m_curDir == UP) {

				if ((m_searchNext[0][0] - m_curImg.length + 1) < 0)
					return false;

				for (int y = m_searchNext[0][1] - m_curImg.length + 2, j = 1; y <= m_searchNext[0][1]; y++, j++)
					for (int x = m_searchNext[0][0] - j; x < m_searchNext[0][0]; x++)
						if (b_imgBoardnext[y][x] == true)
							return false;
			} else if (m_curDir == LEFT) {

				if ((m_searchNext[0][1] + m_curImg.length - 1) >= YSIZE)
					return false;

				for (int y = m_searchNext[0][1] + 1, j = 2; y < m_searchNext[0][1] + m_curImg.length; y++, j++)
					for (int x = m_searchNext[0][0] - m_curImg.length + j; x <= m_searchNext[0][0]; x++)
						if (b_imgBoardnext[y][x] == true)
							return false;
			} else if (m_curDir == DOWN) {

				if ((m_searchNext[0][0] + m_curImg.length - 1) >= XSIZE)
					return false;

				for (int y = m_searchNext[0][1], j = 0; y < m_searchNext[0][1] + m_curImg.length - 1; y++, j++)
					for (int x = m_searchNext[0][0] + 1; x < m_searchNext[0][0] + m_curImg.length - j; x++)
						if (b_imgBoardnext[y][x] == true)
							return false;
			}
		} else if (pushDir == DOWN) {
			if (m_curDir == DOWN) {

				if ((m_searchNext[0][0] - m_curImg.length + 1) < 0)
					return false;

				for (int y = m_searchNext[0][1], j = 1; y < m_searchNext[0][1] + m_curImg.length - 1; y++, j++)
					for (int x = m_searchNext[0][0] - m_curImg.length + j; x < m_searchNext[0][0]; x++)
						if (b_imgBoardnext[y][x] == true)
							return false;
			} else if (m_curDir == RIGHT) {

				if ((m_searchNext[0][1] + m_curImg.length - 1) >= YSIZE)
					return false;

				for (int y = m_searchNext[0][1] + 1, j = 1; y < m_searchNext[0][1] + m_curImg.length; y++, j++)
					for (int x = m_searchNext[0][0]; x < m_searchNext[0][0] + m_curImg.length - j; x++)
						if (b_imgBoardnext[y][x] == true)
							return false;
			} else if (m_curDir == UP) {

				if ((m_searchNext[0][0] + m_curImg.length - 1) >= XSIZE)
					return false;

				for (int y = m_searchNext[0][1] - m_curImg.length + 2, j = 1; y <= m_searchNext[0][1]; y++, j++)
					for (int x = m_searchNext[0][0] + 1; x <= m_searchNext[0][0] + j; x++)
						if (b_imgBoardnext[y][x] == true)
							return false;
			}
		}
		return true;
	}

	public boolean isinboard(int m_curX, int m_curY) {
		if ((m_curX > -1) && (m_curX < XSIZE) && (m_curY > -1) && (m_curY < YSIZE)) // Is it in board?
		{
			return true;
		}

		return false;
	}

	// _________________________________________________________________________
	//
	// Name : initializeSearchBoard()
	//
	// To initialize the searched board
	// _________________________________________________________________________
	public void initializeSearchBoard(int m_curX, int m_curY) {
		if (isinboard(m_curX, m_curY) && // Is it in board?
				(b_searchBoard[m_curY][m_curX] == true)) // Is it searched?
		{
			b_searchBoard[m_curY][m_curX] = false;

			initializeSearchBoard(m_curX, m_curY - 1); // UP
			initializeSearchBoard(m_curX - 1, m_curY); // LEFT
			initializeSearchBoard(m_curX, m_curY + 1); // DOWN
			initializeSearchBoard(m_curX + 1, m_curY); // RIGHT
		}
	}

	public void removePuyo(int m_curX, int m_curY) {
		if (isinboard(m_curX, m_curY) && // Is it in board?
				(b_searchBoard[m_curY][m_curX] == true)) // Is it searched?
		{
			m_deletecnt[m_curX]++;
			b_searchBoard[m_curY][m_curX] = false;
			b_imgBoardnext[m_curY][m_curX] = false;

			removePuyo(m_curX, m_curY - 1); // UP
			removePuyo(m_curX - 1, m_curY); // LEFT
			removePuyo(m_curX, m_curY + 1); // DOWN
			removePuyo(m_curX + 1, m_curY); // RIGHT
		}
	}

	public int searchPuyo(int m_curX, int m_curY, int m_color) {
		if (isinboard(m_curX, m_curY) && // Is it in board?
				(b_searchBoard[m_curY][m_curX] == false) && // Is it searched?
				(b_imgBoardnext[m_curY][m_curX] == true) && // Is it effective
				(m_imgBoardnext[m_curY][m_curX] == m_color)) // Is it the color we want?
		{
			b_searchBoard[m_curY][m_curX] = true;
			return (1 + searchPuyo(m_curX, m_curY - 1, m_color) + // UP
					searchPuyo(m_curX - 1, m_curY, m_color) + // LEFT
					searchPuyo(m_curX, m_curY + 1, m_color) + // DOWN
					searchPuyo(m_curX + 1, m_curY, m_color)); // RIGHT
		}

		return 0;
	}

	public synchronized void updatePuyoBoard() {
		m_searchCnt = m_curImg.length;
		while (m_searchCnt > 0) {
			for (int i = 0; i < m_searchCnt; i++) {
				if (searchPuyo(m_searchNext[i][0], m_searchNext[i][1],
						m_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]]) >= REMSIZE) {
					removePuyo(m_searchNext[i][0], m_searchNext[i][1]);
				}

				initializeSearchBoard(m_searchNext[i][0], m_searchNext[i][1]);
			}
			restoreBoard();
		}
	}

	public void restoreBoard() {
		m_searchCnt = 0;
		for (int x = 0; x < XSIZE; x++) {
			int ymax = m_bottomlimit[x];
			for (int y = YSIZE - 1; y > ymax; y--) {
				if (b_imgBoardnext[y][x] == false) {
					for (int yt = y - 1; yt > ymax; yt--) {
						if (b_imgBoardnext[yt][x] == true) {
							b_imgBoardnext[yt][x] = false;

							m_imgBoardnext[y][x] = m_imgBoardnext[yt][x];
							b_imgBoardnext[y][x] = true;

							m_searchNext[m_searchCnt][0] = x;
							m_searchNext[m_searchCnt][1] = y;
							m_searchCnt++;
							break;
						}
					}

				}
			}
			m_bottomlimit[x] = m_bottomlimit[x] + m_deletecnt[x];
			m_deletecnt[x] = 0;
		}
	}

	public synchronized void changePuyoPos(int xinc, int yinc) {
		if (testCollision(xinc, yinc) == 2) {
			if ((xinc == 0) && (yinc == 1)) {
				m_bSpaceKey = true;
				checkKey();
				m_bSpaceKey = false;

			}
		} else if (testCollision(xinc, yinc) == 3) {
			for (int i = 0; i < m_curImg.length; i++) {
				if ((m_searchNext[i][1] > -1)) {
					b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;

					m_searchNext[i][0] = m_searchNext[i][0] + xinc;
					m_searchNext[i][1] = m_searchNext[i][1] + yinc;
				} else {
					m_searchNext[i][0] = m_searchNext[i][0] + xinc;
					m_searchNext[i][1] = m_searchNext[i][1] + yinc;
				}
			}

			// To renew the old board.
			for (int i = 0; i < m_curImg.length; i++) {
				if ((m_searchNext[i][1] > -1)) {
					m_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = m_curImg[i];
					b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = true;
				}
			}
		}
	}

	private synchronized void checkKey() {
		if (m_bUpKey) {
			if (testCollisionShape(UP)) {
				switch (m_curDir) {
				case UP:
					m_curDir = LEFT;
					for (int i = 1; i < m_curImg.length; i++) {
						if ((m_searchNext[i][1] > -1)) {
							b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;
						}
						m_searchNext[i][0] = m_searchNext[0][0] - i;
						m_searchNext[i][1] = m_searchNext[0][1];
					}

					break;
				case LEFT:
					m_curDir = DOWN;
					for (int i = 1; i < m_curImg.length; i++) {
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;
						m_searchNext[i][0] = m_searchNext[0][0];
						m_searchNext[i][1] = m_searchNext[0][1] + i;
					}

					break;
				case DOWN:
					m_curDir = RIGHT;
					for (int i = 1; i < m_curImg.length; i++) {
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;
						m_searchNext[i][0] = m_searchNext[0][0] + i;
						m_searchNext[i][1] = m_searchNext[0][1];
					}

					break;
				case RIGHT:
					m_curDir = UP;
					for (int i = 1; i < m_curImg.length; i++) {
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;
						m_searchNext[i][0] = m_searchNext[0][0];
						m_searchNext[i][1] = m_searchNext[0][1] - i;
					}

					break;
				default:
					break;
				}

				for (int i = 1; i < m_curImg.length; i++) {
					if ((m_searchNext[i][1] > -1)) {
						m_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = m_curImg[i];
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = true;
					}
				}
			}
		} else if (m_bDownKey) {
			if (testCollisionShape(DOWN)) {
				switch (m_curDir) {
				case DOWN:
					m_curDir = LEFT;
					for (int i = 1; i < m_curImg.length; i++) {
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;
						m_searchNext[i][0] = m_searchNext[0][0] - i;
						m_searchNext[i][1] = m_searchNext[0][1];
					}

					break;
				case RIGHT:
					m_curDir = DOWN;
					for (int i = 1; i < m_curImg.length; i++) {
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;
						m_searchNext[i][0] = m_searchNext[0][0];
						m_searchNext[i][1] = m_searchNext[0][1] + i;
					}

					break;
				case UP:
					m_curDir = RIGHT;
					for (int i = 1; i < m_curImg.length; i++) {
						if ((m_searchNext[i][1] > -1)) {
							b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;
						}
						m_searchNext[i][0] = m_searchNext[0][0] + i;
						m_searchNext[i][1] = m_searchNext[0][1];
					}

					break;
				case LEFT:
					m_curDir = UP;
					for (int i = 1; i < m_curImg.length; i++) {
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;

						m_searchNext[i][0] = m_searchNext[0][0];
						m_searchNext[i][1] = m_searchNext[0][1] - i;
					}

					break;
				default:
					break;
				}

				for (int i = 1; i < m_curImg.length; i++) {
					if ((m_searchNext[i][1] > -1)) {
						m_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = m_curImg[i];
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = true;
					}
				}
			}
		} else if (m_bLeftKey) {
			changePuyoPos(-1, 0);
		} else if (m_bRightKey) {
			changePuyoPos(1, 0);
		} else if (m_bSpaceKey) {
			if (m_curDir == DOWN) {
				for (int i = m_curImg.length - 1; i >= 0; i--) {
					if (m_searchNext[i][1] > -1)
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;

					m_searchNext[i][1] = m_bottomlimit[m_searchNext[i][0]]--;

					m_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = m_curImg[i];
					b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = true;

					if (m_bottomlimit[m_searchNext[i][0]] < m_bottommin)
						m_bottommin = m_bottomlimit[m_searchNext[i][0]];
					if (m_bottomlimit[m_searchNext[i][0]] > m_bottommax)
						m_bottommax = m_bottomlimit[m_searchNext[i][0]];
				}
			} else {
				for (int i = 0; i < m_curImg.length; i++) {
					if (m_searchNext[i][1] > -1)
						b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = false;

					m_searchNext[i][1] = m_bottomlimit[m_searchNext[i][0]]--;

					m_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = m_curImg[i];
					b_imgBoardnext[m_searchNext[i][1]][m_searchNext[i][0]] = true;

					if (m_bottomlimit[m_searchNext[i][0]] < m_bottommin)
						m_bottommin = m_bottomlimit[m_searchNext[i][0]];
					if (m_bottomlimit[m_searchNext[i][0]] > m_bottommax)
						m_bottommax = m_bottomlimit[m_searchNext[i][0]];
				}
			}

			updatePuyoBoard();
			updateCurImg();
		} else if (m_bEscKey) {

			System.exit(0);
		}
	}

	public void initGameFrame() {
		for (int y = 0; y < YSIZE; y++)
			for (int x = 0; x < XSIZE; x++) {
				m_imgBoardnext[y][x] = -1;
				b_imgBoardnext[y][x] = false;
				b_searchBoard[y][x] = false;
			}

		for (int i = 0; i < XSIZE; i++) {
			m_bottomlimit[i] = YSIZE - 1;
			m_deletecnt[i] = 0;
		}

		m_curDir = UP;
	}

	public void updateCurImg() {
		for (int i = 0; i < DROPCNT; i++)
			m_curImg[i] = m_spareImg[i];

		for (int i = DROPCNT; i < m_sparecnt * DROPCNT; i++)
			m_spareImg[i - DROPCNT] = m_spareImg[i];

		for (int i = (m_sparecnt - 1) * DROPCNT; i < m_sparecnt * DROPCNT; i++)
			m_spareImg[i] = (int) (r1.nextDouble() * PUYOSIZE);

		for (int i = 0, y = 0; i < m_curImg.length; i++) {
			m_searchNext[i][0] = sxcnt;
			m_searchNext[i][1] = y--;

			if ((m_searchNext[i][1] > -1)) {
				m_imgBoardnext[m_searchNext[i][1]][sxcnt] = m_curImg[i];
				b_imgBoardnext[m_searchNext[i][1]][sxcnt] = true;
			}
		}
		m_curDir = UP;

	}

	class KeyListener extends KeyAdapter {

		public void keyTyped(KeyEvent evt) {
		}

		public void keyPressed(KeyEvent evt) {
			int key = evt.getKeyCode();

			if (key == KeyEvent.VK_UP)
				m_bUpKey = true;
			else if (key == KeyEvent.VK_DOWN)
				m_bDownKey = true;
			else if (key == KeyEvent.VK_LEFT)
				m_bLeftKey = true;

			else if (key == KeyEvent.VK_RIGHT)
				m_bRightKey = true;
			else if (key == KeyEvent.VK_SPACE)
				m_bSpaceKey = true;
			else if (key == KeyEvent.VK_ESCAPE) {
				m_bEscKey = true;

				System.exit(0);
			} else if (key == KeyEvent.VK_R)
				m_bRKey = true;

			if (bNext)
				checkKey();
		}

		public void keyReleased(KeyEvent evt) {
			int key = evt.getKeyCode();

			if (key == KeyEvent.VK_UP)
				m_bUpKey = false;
			else if (key == KeyEvent.VK_DOWN)
				m_bDownKey = false;
			else if (key == KeyEvent.VK_LEFT)
				m_bLeftKey = false;
			else if (key == KeyEvent.VK_RIGHT)
				m_bRightKey = false;
			else if (key == KeyEvent.VK_ESCAPE)
				m_bEscKey = false;
			else if (key == KeyEvent.VK_R)
				m_bRKey = false;
		}
	}

}