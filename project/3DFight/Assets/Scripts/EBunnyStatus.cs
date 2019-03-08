using UnityEngine;
using System.Collections;

//怪物状态
public class EBunnyStatus : MonoBehaviour {
    public float health = 10f;
    public float maxHealth = 10f;

    public int numHeldItemMin = 1;  //随机最小值
    public int numHeldItemMax = 3;  //随机最大值
    public GameObject pickUp1;
    public GameObject pickUp2;

    private bool isDead = false;
    private Animation animation;

    void Start()
    {
        animation = GetComponent<Animation>();
    }

    public void ApplyDamage(float damage)
    {
        if (health <= 0)
        {
            return;
        }

        health -= damage;
        animation.Play("EBunny_Hit");

        if (health <= 0 && !isDead)
        {
            health = 0;
            isDead = true;
            StartCoroutine( Die());
        }
    }

    IEnumerator Die()
    {
        animation.Stop();
        animation.Play("EBunny_Death");
        Destroy(this.GetComponent<EnemyAICtroller>());

        yield return new WaitForSeconds(2f);

        Vector3 itemLocation = this.transform.position;  //获得当前怪物的死亡地点
        int reWardItems = Random.Range(numHeldItemMin, numHeldItemMax);
        for (int i = 0; i < reWardItems; i++)
        {
            Vector3 randomItemLocation = itemLocation;
            randomItemLocation += new Vector3(Random.Range(-2, 2), 1f, Random.Range(-2, 2));  //掉落物品在怪物周围随机分布
            if (Random.value > 0.5)
            {
                Instantiate(pickUp1, randomItemLocation, pickUp1.transform.rotation);
            }
            else
            {
                Instantiate(pickUp2, randomItemLocation, pickUp2.transform.rotation);
            }
        }
        Destroy(this.gameObject);
    }

    public bool isdead()
    {
        return isDead;
    }
	
}
