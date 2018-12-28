using UnityEngine;
using UnityEngine.UI;
using System.Collections;
using UnityEngine.SceneManagement;
public class basic1 : MonoBehaviour {
	public static float T;
	GameObject text;
	Rigidbody2D rigid2D;
	float jumpForce = 250.0f;
	float walkForce = 20.0f;
	float maxWalkSpeed = 2.0f;
	float jumpbonus =1.0f;
	Animator animator;
	int c =0;
	GameObject arrowPrefab;
	GameObject flag;
	GameObject springcloud;
	GameObject Myla;
	float span =1.0f;
	float delta =0;
	Scene s;
	Vector3 curPos;
	public GameObject arrowPre;

	int at= 0;
	// Use this for initialization
	void Start () {
		this.flag = GameObject.Find("flag");
		this.arrowPrefab = GameObject.Find("arrowPrefab(Clone)");
		this.springcloud = GameObject.Find ("springcloud");
		this.text = GameObject.Find ("Text"); 

		this.rigid2D = GetComponent<Rigidbody2D> ();
		this.animator = GetComponent<Animator> ();
		s = SceneManager.GetActiveScene();
		Debug.Log ("활성 :" + s.name);
		this.Myla = GameObject.Find ("Myla");
	}

	// Update is called once per frame
	void Update () {
		T += Time.deltaTime;
		this.text.GetComponent<Text> ().text = "Time : "+(int)(T / 60) + ":" + (T % 60.0f).ToString("F2");
		if (Input.GetKeyDown (KeyCode.UpArrow)&& c<1) {
			c++;
			this.animator.SetTrigger ("JumpTrigger");
			this.rigid2D.AddForce (transform.up * this.jumpForce *this.jumpbonus);
			jumpbonus =1.0f;

		}
		float speedx = Mathf.Abs (this.rigid2D.velocity.x);

		if (this.rigid2D.velocity.y == 0) {
			c = 0;
			this.animator.speed = speedx / 2.0f;
		} else {
			this.animator.speed = 1.0f;
		}
		int key = 0;
		if (Input.GetKey (KeyCode.RightArrow)) {
			key = 1;
			at = 1;
			//this.transform.Translate (0.05f,0,0);
		}
		if (Input.GetKey (KeyCode.LeftArrow)) {
			key = -1;
			at = -1;
			//this.transform.Translate (-0.05f,0,0);
		}
		if (Input.GetKey (KeyCode.Escape)) {
			SceneManager.LoadScene ("Sa");
		}


		if(speedx <this.maxWalkSpeed){
			this.rigid2D.AddForce(transform.right * key *this.walkForce);
		}
		if (key != 0) {
			transform.localScale = new Vector3 (key, 1, 1);
		}
		this.animator.speed = speedx / 2.0f;
		if (transform.position.y < -10) {
			SceneManager.LoadScene (s.name);
		}
		/*if (Input.GetKeyDown (KeyCode.Space)) {
			GameObject go = Instantiate (arrowPre) as GameObject;
			if(at>=0)
				go.transform.position = new Vector3 (this.transform.position.x+1.0f, this.transform.position.y, 0);
			else
				go.transform.position = new Vector3 (this.transform.position.x-1.0f, this.transform.position.y, 0);
		}*/


	}
	void OnTriggerEnter2D(Collider2D other){

		if(other.gameObject.Equals (flag)){
			Debug.Log ("골");
			SceneManager.LoadScene ("basic2");
		}

	}
	void OnCollisionEnter2D(Collision2D other){
		if (other.gameObject.Equals (springcloud)) {
			this.jumpbonus = 2.0f;
		}
	}

	public int getDirectionFlag(){
		if (this.at == 0) {
			return 1;
		}
		return this.at;
	}
}

