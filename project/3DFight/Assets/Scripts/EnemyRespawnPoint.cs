using UnityEngine;
using System.Collections;

public class EnemyRespawnPoint : MonoBehaviour {

    public float spawnRange = 40f;  //怪物出生范围
    public GameObject enemy;  //初始化的怪物

    private Transform target;  //目标
    private GameObject currentEnemy;  //当前的怪物
    private bool isOutSideRange = true;  //是否在怪物出生点的范围
    private Vector3 distanceToPlayer;  //

    private void Start()
    {
        target = GameObject.FindGameObjectWithTag("Player").transform;
    }

    void Update()
    {
        distanceToPlayer = transform.position - target.position;

        if (distanceToPlayer.magnitude < spawnRange)
        {
            if (!currentEnemy)
            {
                currentEnemy = Instantiate(enemy, transform.position, transform.rotation) as GameObject;
            }
            isOutSideRange = false;           
        }
        else
        {
            if (currentEnemy)
            {
                Destroy(currentEnemy);
            }
        }
        isOutSideRange = true;
    }
}
