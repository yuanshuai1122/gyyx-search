import React, {useState} from 'react';
import { Tree } from 'antd';
import type { DataNode, DirectoryTreeProps } from 'antd/es/tree';

const { DirectoryTree } = Tree;

const initTreeData: DataNode[] = [
    {
        title: 'redis-1',
        key: '0-0',
    },
    {
        title: 'redis-2',
        key: '0-1',
        children: [
            { title: '0', key: '0-1-0', isLeaf: true, },
            { title: '1', key: '0-1-1', isLeaf: true },
            { title: '2', key: '0-1-2', isLeaf: true },
            { title: '3', key: '0-1-3', isLeaf: true },
            { title: '4', key: '0-1-4', isLeaf: true },
            { title: '5', key: '0-1-5', isLeaf: true },
            { title: '6', key: '0-1-6', isLeaf: true },
            { title: '7', key: '0-1-7', isLeaf: true },
            { title: '8', key: '0-1-8', isLeaf: true },
            { title: '9', key: '0-1-9', isLeaf: true },
        ],
    },
];

const updateTreeData = (list: DataNode[], key: React.Key, children: DataNode[]): DataNode[] =>
    list.map((node) => {
        // 如果node的key等于传入的key 则将children展开
        if (node.key === key) {
            return {
                ...node,
                children,
            };
        }
        // 如果
        if (node.children) {
            return {
                ...node,
                children: updateTreeData(node.children, key, children),
            };
        }
        return node;
    });

const RedisTree: React.FC = () => {

    const [treeData, setTreeData] = useState(initTreeData);


    const onSelect: DirectoryTreeProps['onSelect'] = (keys, info) => {
        console.log('Trigger Select', keys, info.node);
        // 如果被选择的是叶子节点 则调用接口查询
        if (info.node.isLeaf) {
            console.log("开始通过key查询value")
        }
    };

    const onLoadData = ({ key, children }: any) =>
        new Promise<void>((resolve) => {
            console.log("key:" + key, "children:" + children)
            if (children) {
                resolve();
                return;
            }
            setTimeout(() => {
                setTreeData((origin) =>
                    // 这里的children 查接口 接口要返回 title 和 是否为叶子节点
                    updateTreeData(origin, key, [
                        { title: 'Child Node', key: `${key}-0`, isLeaf: true },
                        { title: 'Child Node', key: `${key}-1`, isLeaf: true },
                        { title: 'Child Node', key: `${key}-2` },
                        { title: 'Child Node', key: `${key}-3` },
                        { title: 'Child Node', key: `${key}-4` },
                        { title: 'Child Node', key: `${key}-5` },
                    ]),
                );

                resolve();
            }, 1000);
        });

    return (
        <DirectoryTree
            onSelect={onSelect}
            treeData={treeData}
            loadData={onLoadData}
        />
    );
};

export default RedisTree;