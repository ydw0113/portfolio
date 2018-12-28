using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;
public class ArrowAttack : MonoBehaviour {
	int directionFlag;
	bool Pos= true;
	GameObject Myla;
	GameObject Myla2;
	GameObject nala;
	GameObject cat;
	float span =1.0f;
	float delta =0;
	int ko=0;
	Scene s;
	int count=0;
	// Use this for initialization
	void Start () {
		s = SceneManager.GetActiveScene();
		this.cat = GameObject.Find ("cat");
		this.Myla = GameObject.Find ("Myla");
		this.Myla2 = GameObject.Find ("Myla2");
		this.nala = GameObject.Find ("nala");

		this.directionFlag = GameObject.Find("cat").GetComponent<basic2>().getDirectionFlag();



	}

	// Update is called once per frame
	void Update () {
		if (directionFlag == 1) {
			this.transform.Translate (0.3f, 0, 0);
		} else if (directionFlag ==-1 ) {
			this.transform.localScale =new Vector3(-1, 1, 1);
			this.transform.Translate (-0.3f, 0, 0);

		}
		this.delta += Time.deltaTime;

		if (this.delta > span &&ko==1) {
			//Debug.Log (delta2);

			Destroy (Myla);

			this.delta = 0;
		}
		//화살제거
		if (this.transform.position.x > 10.0f || this.transform.position.x < -10.0f) {
		Destroy (gameObject);
		}

	}
	//몬스터와 충돌
	void  OnTriggerEnter2D(Collider2D other){
		if(!other.gameObject.Equals (cat))
		Destroy (gameObject);
		if (other.gameObject.Equals (Myla)) {

			ko = 1;	
				Destroy (other.gameObject);
			}
		if (other.gameObject.Equals (Myla2)) {

			ko = 1;	
			Destroy (other.gameObject);
		}
		if (other.gameObject.Equals (nala)) {
			Debug.Log ("12");
				Destroy (other.gameObject);

		
		}
	}
}