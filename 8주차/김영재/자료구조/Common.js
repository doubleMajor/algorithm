const disabledButton = (id) => document.getElementById(id).disabled = true;

const enabledButton = (id) => document.getElementById(id).disabled = false;


const show = (exampleList, exampleId) => {
    exampleList.filter(example => example.id !== exampleId && document.getElementById(example.name) != null)
                .forEach(example => document.getElementById(example.name).className = 'hide');
    document.getElementById(exampleList.find(example => example.id === exampleId).name).className = 'show';
}


const createPTag = (content, className) => {
    const pTag = document.createElement('p');
    pTag.innerHTML = content;
    pTag.className = className;
    return pTag;
}


const createSpanTag = (content, className) => {
    const spanTag = document.createElement('span');
    spanTag.innerHTML = content;
    spanTag.className = className;
    return spanTag;
}

const createTrTag = (className) => {
    const trTag = document.createElement('tr');

    trTag.className = className;
    return trTag;
}

const createTdTag = (contnent, className) => {
    const tdTag = document.createElement('td');
    
    tdTag.innerHTML = contnent;
    tdTag.className = className;
    return tdTag;
}


const createDivTag = (content, className) => {
    const divTag = document.createElement('div');
    divTag.innerHTML = content;
    divTag.className = className;
    return divTag;
}

const addDescription = (id, element) => {
    document.getElementById(id).append(element);
}

const allRemoveChildren = (id) => {
    const element = document.getElementById(id);
    while(element.hasChildNodes()) {
        element.removeChild(element.firstChild);
    }
}

