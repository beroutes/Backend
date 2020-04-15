import { element, by, ElementFinder } from 'protractor';

export class UbicacionesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-ubicaciones div table .btn-danger'));
  title = element.all(by.css('jhi-ubicaciones div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getText();
  }
}

export class UbicacionesUpdatePage {
  pageTitle = element(by.id('jhi-ubicaciones-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  tituloInput = element(by.id('field_titulo'));
  descripcionInput = element(by.id('field_descripcion'));
  coordenadaXInput = element(by.id('field_coordenadaX'));
  coordenadaYInput = element(by.id('field_coordenadaY'));
  duracionInput = element(by.id('field_duracion'));
  qrInput = element(by.id('field_qr'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setTituloInput(titulo: string): Promise<void> {
    await this.tituloInput.sendKeys(titulo);
  }

  async getTituloInput(): Promise<string> {
    return await this.tituloInput.getAttribute('value');
  }

  async setDescripcionInput(descripcion: string): Promise<void> {
    await this.descripcionInput.sendKeys(descripcion);
  }

  async getDescripcionInput(): Promise<string> {
    return await this.descripcionInput.getAttribute('value');
  }

  async setCoordenadaXInput(coordenadaX: string): Promise<void> {
    await this.coordenadaXInput.sendKeys(coordenadaX);
  }

  async getCoordenadaXInput(): Promise<string> {
    return await this.coordenadaXInput.getAttribute('value');
  }

  async setCoordenadaYInput(coordenadaY: string): Promise<void> {
    await this.coordenadaYInput.sendKeys(coordenadaY);
  }

  async getCoordenadaYInput(): Promise<string> {
    return await this.coordenadaYInput.getAttribute('value');
  }

  async setDuracionInput(duracion: string): Promise<void> {
    await this.duracionInput.sendKeys(duracion);
  }

  async getDuracionInput(): Promise<string> {
    return await this.duracionInput.getAttribute('value');
  }

  async setQrInput(qr: string): Promise<void> {
    await this.qrInput.sendKeys(qr);
  }

  async getQrInput(): Promise<string> {
    return await this.qrInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class UbicacionesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-ubicaciones-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-ubicaciones'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
