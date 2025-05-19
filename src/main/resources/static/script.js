async function addBus() {
    const bus = {
        busNumber: document.getElementById("busNumber").value,
        driverName: document.getElementById("driverName").value,
        routeNumber: document.getElementById("routeNumber").value
    };
    await fetch("/api/buses", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(bus)
    });
    loadDepot("bus");
    loadRoute("bus");
}

async function loadDepot(sort) {
    const res = await fetch("/api/buses/depot?sortBy=" + sort);
    const data = await res.json();
    const table = document.getElementById("depotTable").getElementsByTagName("tbody")[0];
    table.innerHTML = "";
    data.forEach(bus => {
        table.innerHTML += `<tr><td>${bus.busNumber}</td><td>${bus.driverName}</td><td>${bus.routeNumber}</td><td><button onclick="move(${bus.busNumber}, 'route')">Виїзд</button></td></tr>`;
    });
}

async function loadRoute(sort) {
    const res = await fetch("/api/buses/route?sortBy=" + sort);
    const data = await res.json();
    const table = document.getElementById("routeTable").getElementsByTagName("tbody")[0];
    table.innerHTML = "";
    data.forEach(bus => {
        table.innerHTML += `<tr><td>${bus.busNumber}</td><td>${bus.driverName}</td><td>${bus.routeNumber}</td><td><button onclick="move(${bus.busNumber}, 'depot')">Повернення</button></td></tr>`;
    });
}

async function move(busNumber, to) {
    await fetch("/api/buses/" + busNumber + "/move?to=" + to, {method: "PUT"});
    loadDepot("bus");
    loadRoute("bus");
}

window.onload = () => {
    loadDepot("bus");
    loadRoute("bus");
};