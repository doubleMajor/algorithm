let exampleHashTable;
let exampleChaningHashTable;

const createHash = () => {
    const planeText = document.getElementById("planeText").value;
    const hashText = hash(planeText);
    const spanTag = createSpanTag(`${planeText} : ${hashText}`);
    addDescription('hashTextWrapper', spanTag);
}

const hash = (str) => {
    let hash = 31;
    for(let i = 0; i < str.length; i++) {
        //charCodeAt = 해당 인덱스에 해당하는 값의 유니코드 값을 리턴
        hash *= str.charCodeAt(i);
    }
    return hash;
}


class HashTable {
    constructor (size) {
        //미리 공간을 만들어 놓는다.
        this.table = new Array(size).fill({key : null, value : -1});
    }

    setItem(key, value) {
        const index = hash(key) % this.table.length;
        console.log(`index : ${index}`);
        console.log(this.table)
        this.table[index] = {index, key, value};
    }

    getItem(key) {
        const index = hash(key) % this.table.length;
        const data = this.table[index];
        return data.key === key ? data.value : null;
    }
}

const createHashTableData = (index, key, value, parentId) => {
    const trTag = createTrTag();
    const indexTdTag = createTdTag(index);
    const keyTdTag = createTdTag(key);
    const valueTdTag = createTdTag(value);
    trTag.append(indexTdTag, keyTdTag, valueTdTag);
    
    addDescription(parentId, trTag);
}

const createHashTable = () => {
    allRemoveChildren('printHashTable');
    const size = document.getElementById('hashTableSize').value | 1;
    exampleHashTable = new HashTable(size);
    console.log(exampleHashTable)
    createHashTableData('index', 'key', 'value', 'printHashTable')
}

const set = () => {
    const [ key, value ] = [document.getElementById('inputKey').value, document.getElementById('inputValue').value];
    console.log(exampleHashTable)
    exampleHashTable.setItem(key, value);
    console.log(exampleHashTable)
    allRemoveChildren('printHashTable');

    createHashTableData('index', 'key', 'value', 'printHashTable')
    exampleHashTable.table.sort((a,b) => a.index-b.index).filter(table => table.key != null).forEach(table => {
        console.log(table);
        createHashTableData(table.index, table.key, table.value, 'printHashTable')
    })
}

const get = () => {
    allRemoveChildren('outPutHashTableWrapper');
    const key = document.getElementById('outputKey').value;
    const value = exampleHashTable.getItem(key);
    const spanTag = createSpanTag(`${key} : ${value}`);
    addDescription('outPutHashTableWrapper', spanTag);
}

const createChaningHashTableData = (index, hashTable, parentId) => {
    const trTag = createTrTag();
    const indexTdTag = createTdTag(index);
    const keyTdTag = createTdTag(hashTable.data.map(d => `{ ${d.key} : ${d.value} }`).join(','));
    trTag.append(indexTdTag, keyTdTag);
    
    addDescription(parentId, trTag);
}


class ChaingHashTable {
    constructor (size) {
        this.table = new Array(size).fill(-1).map(v => ({index: -1, data: []}));
    }

    setItem(key, value) {
        const index = hash(key) % this.table.length;

        let flag = false;
        const hashData = {index, data : this.table[index].data};

        const data = this.table[index].data?.map(data => {
            if(data.key === key) {
                flag = true;
                data = {key, value};
            }
            return data;
        });
        flag ? hashData.data = data :hashData.data.push({key, value});

        this.table[index] = hashData;
    }

    getItem(key) {
        const index = hash(key) % this.table.length;
        const {data} = this.table[index];
        return data.find(v => v.key === key)?.value || null;
    }
}

const createChainingHashTable = () => {
    allRemoveChildren('printChainingHashTable');
    const size = document.getElementById('chainingHashTableSize').value | 1;
    exampleChaningHashTable = new ChaingHashTable(size);
    console.log(exampleChaningHashTable)
    createChaningHashTableData('index', {data : [{key : "key", value : 'value'}]}, 'printChainingHashTable')
}

const setChaining = () => {
    const [ key, value ] = [document.getElementById('inputChainingKey').value, document.getElementById('inputChainingValue').value];
    console.log(exampleChaningHashTable)
    exampleChaningHashTable.setItem(key, value);
    console.log(exampleChaningHashTable)
    allRemoveChildren('printChainingHashTable');

    createChaningHashTableData('index', {data : [{key : "key", value : 'value'}]}, 'printChainingHashTable')
    exampleChaningHashTable.table.filter(table => table.index !== -1).sort((a,b) => a.index-b.index).forEach((table, i) => {
        createChaningHashTableData(table.index, table, 'printChainingHashTable')
    })
}

const getChaining = () => {
    allRemoveChildren('outPutChainingHashTableWrapper');
    const key = document.getElementById('outputChainingKey').value;
    const value = exampleChaningHashTable.getItem(key);
    const spanTag = createSpanTag(`${key} : ${value}`);
    addDescription('outPutChainingHashTableWrapper', spanTag);
}