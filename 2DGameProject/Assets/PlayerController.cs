using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;
public class PlayerController : MonoBehaviour {
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
	int arrowCount=0;
	// Use this for initialization
	void Start () {
		this.flag = GameObject.Find("flag");
		this.arrowPrefab = GameObject.Find("arrowPrefab(Clone)");
		this.springcloud = GameObject.Find ("springcloud");
		this.rigid2D = GetComponent<Rigidbody2D> ();
		this.animator = GetComponent<Animator> ();
	}
	
	// Update is called once per frame
	void Update () {
		
		if (Input.GetKeyDown (KeyCode.UpArrow)&& c<1) {
			c++;

			this.rigid2D.AddForce (transform.up * this.jumpForce *this.jumpbonus);
			jumpbonus =1.0f;

		}
		if (this.rigid2D.velocity.y == 0) {


		}
		int key = 0;
		if (Input.GetKey (KeyCode.RightArrow))
			key = 1;
		if (Input.GetKey (KeyCode.LeftArrow))
			key = -1;
		if (Input.GetKey (KeyCode.Space)) {
		
		}
		float speedx = Mathf.Abs (this.rigid2D.velocity.x);

		if(speedx <this.maxWalkSpeed){
			this.rigid2D.AddForce(transform.right * key * this.walkForce);
		}
		if (key != 0) {
			transform.localScale = new Vector3 (key, 1, 1);
		}
		this.animator.speed = speedx / 2.0f;
		if (transform.position.y < -10) {
			SceneManager.LoadScene ("GameScene");
		}

	}
	void OnTriggerEnter2D(Collider2D other){
		if(other.gameObject.Equals (flag)){
		Debug.Log ("골");
		SceneManager.LoadScene ("ClearScene");
		}
	}
	void OnCollisionEnter2D(Collision2D other){
		if (other.gameObject.Equals (springcloud)) {
			this.jumpbonus = 2.0f;

		}
	}
	public void destroyArrow(){
		this.arrowCount--;
	}

}
