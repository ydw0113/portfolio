using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;
public class explo : MonoBehaviour {
	public GameObject exploprefab;
	GameObject cat;
	float span =1.0f;
	float delta =0;
	GameObject go;
	GameObject Myla;
	GameObject Myla2;
	GameObject ArrowGen;
	GameObject flag;
	Scene s;
	// Use this for initialization
	void Start () {
		this.cat = GameObject.Find ("cat");
		this.Myla = GameObject.Find ("Myla");
		this.Myla2 = GameObject.Find ("Myla2");
		this.flag = GameObject.Find ("flag");
		this.ArrowGen = GameObject.Find ("arrowPrefab(Clone)");
		s = SceneManager.GetActiveScene();
	}

	// Update is called once per frame
	void Update () {
		this.delta += Time.deltaTime;

		if (this.delta > span) {
			//Debug.Log (delta);
			Destroy(go);

			this.delta = 0;
		}
		}
	void OnTriggerEnter2D(Collider2D other){
		//if (other.gameObject.Equals (ArrowGen)) {
		if (other.gameObject.Equals (flag)) {
		} else {
			go = Instantiate (exploprefab) as GameObject;
			go.transform.position = new Vector3 (this.cat.transform.position.x, this.transform.position.y, 0);
		
		}
		//}
	}
	void OnCollisionEnter2D(Collision2D other){
		if (other.gameObject.Equals (Myla)) {
			
			go = Instantiate (exploprefab) as GameObject;
			go.transform.position = new Vector3 (this.cat.transform.position.x, this.transform.position.y, 0);

			SceneManager.LoadScene (s.name);

		}
		if (other.gameObject.Equals (Myla2)) {

			go = Instantiate (exploprefab) as GameObject;
			go.transform.position = new Vector3 (this.cat.transform.position.x, this.transform.position.y, 0);

			SceneManager.LoadScene (s.name);

		}
	}

}
