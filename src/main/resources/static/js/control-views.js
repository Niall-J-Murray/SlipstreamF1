let centre = document.getElementById('centre');
const controlViews = () => {
    window.scrollTo(centre.offsetLeft - 100, 0);
}

const focusOnPickButton = () => {
    window.scrollTo(centre.offsetLeft - 100, 100);
}

function showhide(id) {
    let e = document.getElementById(id);
    e.style.display = (e.style.display == 'block') ? 'none' : 'block';
}